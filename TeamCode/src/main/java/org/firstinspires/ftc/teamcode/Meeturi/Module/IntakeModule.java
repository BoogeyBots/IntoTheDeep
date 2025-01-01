package org.firstinspires.ftc.teamcode.Meeturi.Module;

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
    Servo rotire_left, rotire_right, clapita;
    DcMotorEx motor;

    //public static double power;
    public static double poz = 0;

    public void init() {
        rotire_right = hardwareMap.get(Servo.class, "rotire_right");
        rotire_left = hardwareMap.get(Servo.class, "rotire_left");
        clapita = hardwareMap.get(Servo.class, "clapita");
        motor = hardwareMap.get(DcMotorEx.class, "motor_intake");

        rotire_right.setPosition(0.3);
        rotire_left.setPosition(0.3);
        clapita.setPosition(0.13);

    }

    public void trage(double power) {
        motor.setPower(power);
    }

    public void scuipa(double power) {
        motor.setPower(-power);
    }

    public void stop() {
        motor.setPower(0);
    }

    public void jos() {
        rotire_right.setPosition(0.73);
        rotire_left.setPosition(0.73);
    }

    public void sus() {
        rotire_right.setPosition(0.3);
        rotire_left.setPosition(0.3);
    }

    public void close() {
        clapita.setPosition(0.13);
    }

    public void open() {
        clapita.setPosition(0.6);
    }
}
