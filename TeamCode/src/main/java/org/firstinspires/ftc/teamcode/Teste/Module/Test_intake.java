package org.firstinspires.ftc.teamcode.Teste.Module;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class Test_intake extends LinearOpMode {
    CRServo intake_right, intake_left, intake_roata;
    private Servo rotire_left, rotire_right;
    double modifier = 0.0001;

    @Override
    public void runOpMode() throws InterruptedException {

        intake_right = hardwareMap.get(CRServo.class, "intake_right");
        intake_left = hardwareMap.get(CRServo.class, "intake_left");

        rotire_right = hardwareMap.get(Servo.class, "rotire_right");
        rotire_left = hardwareMap.get(Servo.class, "rotire_left");

        intake_roata = hardwareMap.get(CRServo.class, "intake_roata");

        intake_left.setDirection(DcMotorSimple.Direction.REVERSE);

        rotire_right.setPosition(0.7172);
        rotire_left.setPosition(1 - 0.7172);

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.right_trigger > 0.001) {
                intake_right.setPower(1);
                intake_left.setPower(1);
                intake_roata.setPower(1);
            }

            else if(gamepad1.left_trigger > 0.001) {
                intake_right.setPower(-1);
                intake_left.setPower(-1);
                intake_roata.setPower(-1);
            }

            else {
                intake_right.setPower(0);
                intake_left.setPower(0);
                intake_roata.setPower(0);

            }

            if(gamepad1.a) {
                rotire_right.setPosition(0.49);
                rotire_left.setPosition(1 - 0.49);
            }

            if(gamepad1.b) {
                rotire_right.setPosition(0.3306);
                rotire_left.setPosition(1 - 0.3306);
            }




            //pentru schimbarea poziției servo-urilor sincronizate
            if(gamepad1.x) {
                rotire_left.setPosition(rotire_left.getPosition() - 0.0001);
                rotire_right.setPosition(1 - rotire_left.getPosition());
            }

            if(gamepad1.y) {
                rotire_left.setPosition(rotire_left.getPosition() + 0.0001);
                rotire_right.setPosition(1 - rotire_left.getPosition());
            }

            telemetry.addData("Dreapta: ", rotire_right.getPosition());
            telemetry.addData("Stânga: ", rotire_left.getPosition());
            telemetry.update();
        }
    }
}
