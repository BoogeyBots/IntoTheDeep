package org.firstinspires.ftc.teamcode.Meeturi.Module;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class BratModule {
    HardwareMap hardwareMap;
    public BratModule (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }
    Servo servoST, servoDR;
    Servo servo_gheara, rotire_gheara;

    public void init() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");

        servoDR.setPosition(0.1567);
        servoST.setPosition(0.1567);
        rotire_gheara.setPosition(0.6444);
    }

    public void goDown() {
        servoDR.setPosition(0.1567);
        servoST.setPosition(0.1567);
        rotire_gheara.setPosition(0.6444);
    }

    public void goUp() {
        servoDR.setPosition(0.5789);
        servoST.setPosition(0.5789);
        rotire_gheara.setPosition(0.1211);
    }

    public void coletare_Htech() {
        servoDR.setPosition(0.7967);
        servoST.setPosition(0.8);
        rotire_gheara.setPosition(0.4);
    }

    public void punctare_Htech() {
        servoDR.setPosition(0.5294);
        servoST.setPosition(0.5294);
        rotire_gheara.setPosition(0.1211);
    }

    public void open() {
        servo_gheara.setPosition(0.39);
    }

    public void close() {
        servo_gheara.setPosition(0.517);
    }
}
