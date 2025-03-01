package org.firstinspires.ftc.teamcode.Nationala.Module;

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

    public static double poz = 0;

    public void init() {
        rotire_right = hardwareMap.get(Servo.class, "rotire_right");
        rotire_left = hardwareMap.get(Servo.class, "rotire_left");
        clapita = hardwareMap.get(Servo.class, "clapita");
        motor = hardwareMap.get(DcMotorEx.class, "motor_intake");

        rotire_right.setPosition(0.03);
        rotire_left.setPosition(0.03);
        clapita.setPosition(0.38);

    }

    public void init_auto() {
        rotire_right = hardwareMap.get(Servo.class, "rotire_right");
        rotire_left = hardwareMap.get(Servo.class, "rotire_left");
        clapita = hardwareMap.get(Servo.class, "clapita");
        motor = hardwareMap.get(DcMotorEx.class, "motor_intake");

        rotire_right.setPosition(0.03);
        rotire_left.setPosition(0.03);
        clapita.setPosition(0);

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
        rotire_right.setPosition(0.39);
        rotire_left.setPosition(0.39);
    }

    public void gasire() {
        rotire_right.setPosition(poz);
        rotire_left.setPosition(poz);
    }

    public void sus() {
        rotire_right.setPosition(0.03);
        rotire_left.setPosition(0.03);
    }

    public void close() {
        clapita.setPosition(0.392);
    } //0.392

    public void open() {
        clapita.setPosition(0);
    }
}
