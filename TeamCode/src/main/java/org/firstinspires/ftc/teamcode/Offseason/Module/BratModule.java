package org.firstinspires.ftc.teamcode.Offseason.Module;
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

        servoDR.setPosition(0.14);
        servoST.setPosition(0.14);
        miscare_gheara.setPosition(0.4);
        rotire_gheara.setPosition(0.155);
        servo_gheara.setPosition(0.35);
    }

    public void init_auto() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");
        miscare_gheara = hardwareMap.get(Servo.class, "miscare_gheara");

        servoDR.setPosition(0.13);
        servoST.setPosition(0.13);
        miscare_gheara.setPosition(0.4);
        rotire_gheara.setPosition(0.155);
        servo_gheara.setPosition(0.67);
    }

    public void init_specimene() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");
        miscare_gheara = hardwareMap.get(Servo.class, "miscare_gheara");

        servoDR.setPosition(0.16);
        servoST.setPosition(0.16);
        miscare_gheara.setPosition(0.15);
        rotire_gheara.setPosition(0.815);
        servo_gheara.setPosition(0.67);
    }

    public void brat() {
        servoDR.setPosition(poz_brat);
        servoST.setPosition(poz_brat);
    }

    public void colectare() {
        servoDR.setPosition(0.14);
        servoST.setPosition(0.14);
        miscare_gheara.setPosition(0.4); //0.025
        rotire_gheara.setPosition(0.155);
        servo_gheara.setPosition(0.35);
    }

    public void basket() {
        servoDR.setPosition(0.57);
        servoST.setPosition(0.57);
        miscare_gheara.setPosition(0.11);
        rotire_gheara.setPosition(0.155);
    }

    public void colectare_specimene() {
        servoDR.setPosition(0.92);
        servoST.setPosition(0.92);
        miscare_gheara.setPosition(0.3);
        rotire_gheara.setPosition(0.155);
    }

    public void specimene() {
        servoDR.setPosition(0.14);
        servoST.setPosition(0.14);
        miscare_gheara.setPosition(0.24);
        rotire_gheara.setPosition(0.815);
    }

    public void basketup() {
        servoDR.setPosition(0.52);
        servoST.setPosition(0.52);
        miscare_gheara.setPosition(0.11);//0.045
        rotire_gheara.setPosition(0.155); //0.155
    }

    public void gheara_orizontala() {
        rotire_gheara.setPosition(0.5);
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
        servo_gheara.setPosition(0.35);
    }

    public void close() {
        servo_gheara.setPosition(0.67);
    }

    public void rotire_gheara() {
        rotire_gheara.setPosition(poz_rotire);
    }

    public void miscare_gheara() {
        miscare_gheara.setPosition(poz_miscare);
    }

}