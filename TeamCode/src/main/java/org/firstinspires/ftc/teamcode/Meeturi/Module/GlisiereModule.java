package org.firstinspires.ftc.teamcode.Meeturi.Module;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class GlisiereModule {
    HardwareMap hardwareMap;
    public GlisiereModule (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }
    static double kp = 6, ki = 4.3, kd = 0.8;
    DcMotorEx motorST_ENC, motorDR;
    PIDController controller = new PIDController(kp, ki, kd);

    public void init() {
        motorST_ENC = hardwareMap.get(DcMotorEx.class, "motorST_ENC");
        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");

        motorST_ENC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorDR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorST_ENC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorDR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorDR.setDirection(DcMotorSimple.Direction.REVERSE);

        controller.reset();

        controller.setSetPoint(0);
    }

    public void update() {
        controller.setPID(kp, ki, kd);
        if (!controller.atSetPoint() || controller.getSetPoint() != motorST_ENC.getCurrentPosition()) {
            double output = controller.calculate(
                    motorST_ENC.getCurrentPosition()
            );
            motorST_ENC.setVelocity(output);
            motorDR.setVelocity(output);
        }
    }

    public void basket() {
        controller.setSetPoint(2470);
    }

    public void goDown() {
        controller.setSetPoint(0);
    }

    public void hang() {controller.setSetPoint(850);}

    public void altcv() {controller.setSetPoint(760);}

    public void specimen() {controller.setSetPoint(650);}

}
