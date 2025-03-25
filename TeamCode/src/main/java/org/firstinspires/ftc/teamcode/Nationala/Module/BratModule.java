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

        servoDR.setPosition(0.175);
        servoST.setPosition(0.175);
        miscare_gheara.setPosition(0.39);
        rotire_gheara.setPosition(0.155);
        servo_gheara.setPosition(0.67);
    }

    public void init_auto() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");
        miscare_gheara = hardwareMap.get(Servo.class, "miscare_gheara");

        servoDR.setPosition(0.175);
        servoST.setPosition(0.175);
        miscare_gheara.setPosition(0.39);
        rotire_gheara.setPosition(0.155);
        servo_gheara.setPosition(0.33);
    }

    public void init_specimene() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");
        miscare_gheara = hardwareMap.get(Servo.class, "miscare_gheara");

        servoDR.setPosition(0.13);
        servoST.setPosition(0.13);
        miscare_gheara.setPosition(0.075);
        rotire_gheara.setPosition(0.815);
        servo_gheara.setPosition(0.34);
    }

    public void brat() {
        servoDR.setPosition(poz_brat);
        servoST.setPosition(poz_brat);
    }

    public void colectare() {
        servoDR.setPosition(0.17);
        servoST.setPosition(0.17);
        miscare_gheara.setPosition(0.36); //0.025
        rotire_gheara.setPosition(0.155);
        servo_gheara.setPosition(0.7);
    }

    public void basket() {
        servoDR.setPosition(0.57);
        servoST.setPosition(0.57);
        miscare_gheara.setPosition(0.075);
        rotire_gheara.setPosition(0.155);
    }

    public void colectare_specimene() {
        servoDR.setPosition(0.93);
        servoST.setPosition(0.93);
        miscare_gheara.setPosition(0.25);
        rotire_gheara.setPosition(0.155);
    }

    public void specimene() {
        servoDR.setPosition(0.13);
        servoST.setPosition(0.13);
        miscare_gheara.setPosition(0.19);
        rotire_gheara.setPosition(0.815);
    }

    public void basketup() {
        servoDR.setPosition(0.52);
        servoST.setPosition(0.52);
        miscare_gheara.setPosition(0.045);
        rotire_gheara.setPosition(0.155);
    }

    public void basket_nasol() {
        servoDR.setPosition(0.49);
        servoST.setPosition(0.49);
        miscare_gheara.setPosition(0.045);
        rotire_gheara.setPosition(0.155);
    }

    public void basket_jos() {
        servoDR.setPosition(0.56);
        servoST.setPosition(0.56);
        miscare_gheara.setPosition(0.045);
        rotire_gheara.setPosition(0.155);
    }

    public void rotire_specimene() {
        rotire_gheara.setPosition(0.917);
    }

    public void rotire_sample() {
        rotire_gheara.setPosition(0.13);
    }

    public void open() {
        servo_gheara.setPosition(0.67);
    }

    public void close() {
        servo_gheara.setPosition(0.34);
    }

    public void rotire_gheara() {
        rotire_gheara.setPosition(poz_rotire);
    }

    public void miscare_gheara() {
        miscare_gheara.setPosition(poz_miscare);
    }

}