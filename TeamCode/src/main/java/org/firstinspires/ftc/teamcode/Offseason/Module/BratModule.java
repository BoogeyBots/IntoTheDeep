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
    Servo servo_gheara, box_tube;
    public static double poz_brat, poz_gheara, poz_boxtube;


    public void init() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");

        servoDR.setPosition(0.86);
        servoST.setPosition(0.86);
    }

    public void init_auto() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");

        servoDR.setPosition(0.13);
        servoST.setPosition(0.13);

        servo_gheara.setPosition(0.3);
    }

    public void init_specimene() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");

        servoDR.setPosition(0.16);
        servoST.setPosition(0.16);
        servo_gheara.setPosition(0.3);
    }

    public void colectare() {
        servoDR.setPosition(0.92);//0.13
        servoST.setPosition(0.92);
        servo_gheara.setPosition(0.42);//0.71
    }

    public void basket() {
        servoDR.setPosition(0.4); //0.32
        servoST.setPosition(0.4);
    }

    public void colectare_specimene() {
        servoDR.setPosition(0.92);
        servoST.setPosition(0.92);

    }

    public void specimene() {
        servoDR.setPosition(0.14);
        servoST.setPosition(0.14);

    }

    public void basketup() {
        servoDR.setPosition(0.32);//0.58
        servoST.setPosition(0.32);

    }

    public void basket_nasol() {
        servoDR.setPosition(0.49);
        servoST.setPosition(0.49);

    }

    public void open() {
        servo_gheara.setPosition(0.42);
    }//0.67

    public void close() {
        servo_gheara.setPosition(0.3);
    }

    public void brat() {
        servoDR.setPosition(poz_brat);
        servoST.setPosition(poz_brat);
    }

    public void gheara() {
        servo_gheara.setPosition(poz_gheara);
    }


}