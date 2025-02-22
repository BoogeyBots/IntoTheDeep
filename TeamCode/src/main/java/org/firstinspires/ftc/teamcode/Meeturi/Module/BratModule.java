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
    public static double poz_brat, poz_rotire, poz_gheara;

    public void init() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");

        servoDR.setPosition(0.14); //0.28
        servoST.setPosition(0.14);
        rotire_gheara.setPosition(0.64); //0.65
        servo_gheara.setPosition(0);
    }

    public void init_auto() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");

        servoDR.setPosition(0.14); //0.28
        servoST.setPosition(0.14);
        rotire_gheara.setPosition(0.64); //0.65
        servo_gheara.setPosition(0.5);
    }

    public void brat() {
        servoDR.setPosition(poz_brat);
        servoST.setPosition(poz_brat);
    }

    public void open() {
        servo_gheara.setPosition(0);
    }

    public void close() {
        servo_gheara.setPosition(0.45);
    }

    public void rotire() {
        rotire_gheara.setPosition(poz_rotire);
    }
    public void gheara() {servo_gheara.setPosition(poz_gheara);}

    public void colectare() {
        servoDR.setPosition(0.14); //0.28
        servoST.setPosition(0.14);
        rotire_gheara.setPosition(0.64);
        servo_gheara.setPosition(0);
    }

    public void basket() {
        servoDR.setPosition(0.5);
        servoST.setPosition(0.5);
        rotire_gheara.setPosition(0.1875);
    }

    public void gheara_jos() {
        rotire_gheara.setPosition(0.1);
    }

    public void gheara_sus() {
        rotire_gheara.setPosition(0.2);
    }

    public void auto_specimen() {
        servoDR.setPosition(0.5);
        servoST.setPosition(0.5);
        rotire_gheara.setPosition(0.1);
    }

    public void colectare_specimen_auto() {
        servoDR.setPosition(0.11);
        servoST.setPosition(0.11);
        rotire_gheara.setPosition(0.3);
    }
    public void colectare_specimen_auto_competitie() {
        servoDR.setPosition(0.08);
        servoST.setPosition(0.08);
        rotire_gheara.setPosition(0.3);
    }

    public void intra() {
        servoDR.setPosition(0.5);
        servoST.setPosition(0.5);
        rotire_gheara.setPosition(0.2);
        servo_gheara.setPosition(0.5);
    }

    public void iese() {
        servoDR.setPosition(0.5);
        servoST.setPosition(0.5);
        rotire_gheara.setPosition(0.45);
    }


}