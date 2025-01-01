package org.firstinspires.ftc.teamcode.Meeturi.Module;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class BratModule {
    HardwareMap hardwareMap;
    public BratModule (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }
    Servo servoST, servoDR;
    Servo servo_gheara, rotire_gheara;
    public static double poz_brat, poz_rotire;

    public void init() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");

        servoDR.setPosition(0.29); //0.28
        servoST.setPosition(0.29);
        rotire_gheara.setPosition(0.625); //0.65
        servo_gheara.setPosition(0);

    }

    public void brat() {
        servoDR.setPosition(poz_brat);
        servoST.setPosition(poz_brat);
    }

    public void open() {
        servo_gheara.setPosition(0);
    }

    public void close() {
        servo_gheara.setPosition(0.5);
    }

    public void rotire() {
        rotire_gheara.setPosition(poz_rotire);
    }

    public void colectare() {
        servoDR.setPosition(0.29); //0.28
        servoST.setPosition(0.29);
        rotire_gheara.setPosition(0.625);
        servo_gheara.setPosition(0);
    }

    public void basket() {
        servoDR.setPosition(0.67);
        servoST.setPosition(0.67);
        rotire_gheara.setPosition(0.12);
    }

    public void specimen_punctare() {
        servoDR.setPosition(0.7);
        servoST.setPosition(0.7);
        rotire_gheara.setPosition(0.15);
    }

    public void gheara_jos() {
        rotire_gheara.setPosition(0.07);
    }

    public void punctare_auto() {
        servoDR.setPosition(0.7);
        servoST.setPosition(0.7);
        rotire_gheara.setPosition(0.19);
    }

    public void colectare_specimene() {
        servoDR.setPosition(0.24);
        servoST.setPosition(0.24);
        rotire_gheara.setPosition(0.3);
    }


}
