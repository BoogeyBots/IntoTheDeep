package org.firstinspires.ftc.teamcode.Demo.Module;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeModule {
    HardwareMap hardwareMap;
    public IntakeModule (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }
    CRServo intake_right, intake_left, intake_roata;
    Servo rotire_left, rotire_right;

    public void init() {
        intake_right = hardwareMap.get(CRServo.class, "intake_right");
        intake_left = hardwareMap.get(CRServo.class, "intake_left");

        rotire_right = hardwareMap.get(Servo.class, "rotire_right");
        rotire_left = hardwareMap.get(Servo.class, "rotire_left");

        intake_roata = hardwareMap.get(CRServo.class, "intake_roata");

        intake_left.setDirection(DcMotorSimple.Direction.REVERSE);

        rotire_right.setPosition(0.7172);
        rotire_left.setPosition(1 - 0.7172);

    }

    public void trage() {
        intake_right.setPower(1);
        intake_left.setPower(1);
        intake_roata.setPower(1);
    }

    public void scuipa() {
        intake_right.setPower(-1);
        intake_left.setPower(-1);
        intake_roata.setPower(-1);
    }

    public void stop() {
        intake_right.setPower(0);
        intake_left.setPower(0);
        intake_roata.setPower(0);
    }

    public void jos() {
        rotire_right.setPosition(0.3306);
        rotire_left.setPosition(1 - 0.3306);
    }

    public void sus() {
        rotire_right.setPosition(0.49);
        rotire_left.setPosition(1 - 0.49);
    }
}
