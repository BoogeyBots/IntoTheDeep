package org.firstinspires.ftc.teamcode.Demo.Module;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config

public class IntakeModule {
    HardwareMap hardwareMap;
    public IntakeModule (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }
    Servo rotire_left, rotire_right;
    DcMotorEx motor;

    //public static double power;
    public static double servo_poz = 0.0989;

    public void init() {
        rotire_right = hardwareMap.get(Servo.class, "rotire_right");
        rotire_left = hardwareMap.get(Servo.class, "rotire_left");
        motor = hardwareMap.get(DcMotorEx.class, "motor_intake");

        rotire_right.setPosition(0.0989);
        rotire_left.setPosition(0.0989);

    }

    public void trage(double power) {
        motor.setPower(-power);
    }

    public void scuipa(double power) {
        motor.setPower(power);
    }

    public void stop() {
        motor.setPower(0);
    }

    public void jos() {
        rotire_right.setPosition(servo_poz); //0.3333 && 0.4
        rotire_left.setPosition(servo_poz);
    }

    public void sus() {
        rotire_right.setPosition(0.0989);
        rotire_left.setPosition(0.0989);
    }
}
