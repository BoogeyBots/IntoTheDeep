package org.firstinspires.ftc.teamcode.Nationala.Module;

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
    static double kp = 8, ki = 1.5, kd = 0.12;
    DcMotorEx motor;
    PIDController controller = new PIDController(kp, ki, kd);

    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "motor_extendo");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setDirection(DcMotorSimple.Direction.REVERSE);

        controller.reset();

        controller.setSetPoint(0);
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
        controller.setSetPoint(675);
    }

    public void mediu() {
        controller.setSetPoint(330);
    }
    public void low() {
        controller.setSetPoint(250);
    }
    public void high() {
        controller.setSetPoint(550);
    }
    public void intermediate() {
        controller.setSetPoint(450);
    }
    public void acasa() {
        controller.setSetPoint(0);
    }

}