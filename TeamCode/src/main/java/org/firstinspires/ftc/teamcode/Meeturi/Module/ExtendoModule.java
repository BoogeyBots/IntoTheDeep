package org.firstinspires.ftc.teamcode.Meeturi.Module;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExtendoModule {
    HardwareMap hardwareMap;
    public ExtendoModule (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }
    static double kp = 6, ki = 1.5, kd = 0.3;
    DcMotorEx motor;
    PIDController controller = new PIDController(kp, ki, kd);

    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "motor_extendo");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        controller.reset();

        controller.setSetPoint(-25);
    }

    public void update() {
        controller.setPID(kp, ki, kd);
        if (!controller.atSetPoint() || controller.getSetPoint() != motor.getCurrentPosition()) {
            double output = controller.calculate(
                    motor.getCurrentPosition()
            );
            motor.setVelocity(output);

        }
    }

    public void extinde() {
        controller.setSetPoint(2500);
    }
    public void putin() {controller.setSetPoint(900);}

    public void mediu() {controller.setSetPoint(1225);}
    public void mai_mediu() {controller.setSetPoint(1800);}

    public void acasa() {
        controller.setSetPoint(-25);
    }
    public void nu_mai_vrea() {controller.setSetPoint(-80);}

}
