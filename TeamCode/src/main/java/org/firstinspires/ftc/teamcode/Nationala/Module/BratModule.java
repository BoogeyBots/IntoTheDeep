package org.firstinspires.ftc.teamcode.Nationala.Module;
//0.7 deschis 0.4 inchis
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
    Servo servo_gheara, rotire_gheara, miscare_gheara;
    public static double poz_brat, poz_rotire, poz_miscare;

    public void init() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");
        miscare_gheara = hardwareMap.get(Servo.class, "miscare_gheara");

        servoDR.setPosition(0.08);
        servoST.setPosition(0.08);
        miscare_gheara.setPosition(0.2);
        rotire_gheara.setPosition(0.05);
        servo_gheara.setPosition(0.7);
    }

    public void init_auto() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");
        miscare_gheara = hardwareMap.get(Servo.class, "miscare_gheara");

        servoDR.setPosition(0.0805);
        servoST.setPosition(0.0805);
        miscare_gheara.setPosition(0.2);
        rotire_gheara.setPosition(0.05);
        servo_gheara.setPosition(0.4);
    }

    public void brat() {
        servoDR.setPosition(poz_brat);
        servoST.setPosition(poz_brat);
    }

    public void colectare() {
        servoDR.setPosition(0.0805);
        servoST.setPosition(0.0805);
        miscare_gheara.setPosition(0.2);
        rotire_gheara.setPosition(0.05);
        servo_gheara.setPosition(0.7);
    }

    public void basket() {
        servoDR.setPosition(0.45);
        servoST.setPosition(0.45);
        miscare_gheara.setPosition(0.55);
        rotire_gheara.setPosition(0.05);
    }

    public void open() {
        servo_gheara.setPosition(0.7);
    }

    public void close() {
        servo_gheara.setPosition(0.4);
    }

    public void rotire_gheara() {
        rotire_gheara.setPosition(poz_rotire);
    }

    public void miscare_gheara() {
        miscare_gheara.setPosition(poz_miscare);
    }

}