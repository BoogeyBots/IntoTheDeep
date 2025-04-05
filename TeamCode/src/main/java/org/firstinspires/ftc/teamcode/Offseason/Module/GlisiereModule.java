package org.firstinspires.ftc.teamcode.Offseason.Module;

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
    static double kp = 5.7, ki = 0, kd = 0.05; //4, 0, 0.1
    DcMotorEx motorST_ENC, motorDR;
    PIDController controller = new PIDController(kp, ki, kd);

    public void init() {
        motorST_ENC = hardwareMap.get(DcMotorEx.class, "motorST");
        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");

        motorST_ENC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorDR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorST_ENC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorDR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorDR.setDirection(DcMotorSimple.Direction.REVERSE);

        controller.reset();

        controller.setSetPoint(-20);
    }
    public void init_teleOP() {
        motorST_ENC = hardwareMap.get(DcMotorEx.class, "motorST");
        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");

        motorST_ENC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorDR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorDR.setDirection(DcMotorSimple.Direction.REVERSE);

        controller.setSetPoint(0);
    }

    public void update() {
        controller.setPID(kp, ki, kd);
        if (!controller.atSetPoint() || controller.getSetPoint() != motorDR.getCurrentPosition()) {
            double output = controller.calculate(
                    motorDR.getCurrentPosition()
            );
            motorST_ENC.setVelocity(output);
            motorDR.setVelocity(output);
        }
    }

    public void up() {
        controller.setSetPoint(1200);
    }

    public void up2() {
        controller.setSetPoint(1100);
    }
    public void specimene_mutare() {
        controller.setSetPoint(600);
    }

    public void goDown(int poz) {
        controller.setSetPoint(poz);
    }

    public void hang() {
        controller.setSetPoint(3000);
    }
    public void specimene() {controller.setSetPoint(750);}

    public int encoder_DR() {
        return motorDR.getCurrentPosition();
    }

    public int encoder_ST() {
        return motorST_ENC.getCurrentPosition();
    }



}