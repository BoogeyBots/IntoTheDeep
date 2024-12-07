package org.firstinspires.ftc.teamcode.Teste.Module;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Test_brat extends LinearOpMode {
    Servo servoST, servoDR;
    Servo servo_gheara, rotire_gheara;
    @Override
    public void runOpMode() throws InterruptedException {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        rotire_gheara = hardwareMap.get(Servo.class, "rotire_gheara");

        servoDR.setPosition(0.1567);
        servoST.setPosition(0.1567);
        servo_gheara.setPosition(0.39);
        rotire_gheara.setPosition(0.6444);

        ElapsedTime timp = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        boolean inchis = false;

        /*
        Pentru servo-ul de rotire al ghearei
        Fizic min -> 0.185
        Poziție punctare: 0.39
        Poziție preluare: 0.78
         */


        waitForStart();

        while (opModeIsActive()) {
            timp.startTime();
            if (gamepad1.right_bumper) {
                timp.reset();
                inchis = true;
                servo_gheara.setPosition(0.517);
            }

            if(inchis && timp.seconds() > 3) {
                rotire_gheara.setPosition(0.1211);
                servoDR.setPosition(0.5789);
                servoST.setPosition(0.5789);
                inchis = false;
            }

            if (gamepad1.left_bumper) {
                servo_gheara.setPosition(0.39);
                rotire_gheara.setPosition(0.6444);
                servoDR.setPosition(0.1567);
                servoST.setPosition(0.1567);
            }

            if(gamepad1.a) {
                servo_gheara.setPosition(0.39);
            }

            if(gamepad1.b) {
                servo_gheara.setPosition(0.517);
            }


            /*if (gamepad1.left_bumper) {
                servoDR.setPosition(0.1567);
                servoST.setPosition(0.1567);
            }

             */

            if(gamepad1.a) {
                servoDR.setPosition(servoDR.getPosition() + 0.0001);
                servoST.setPosition(servoST.getPosition() + 0.0001);
            }
            if(gamepad1.b) {
                servoDR.setPosition(servoDR.getPosition() - 0.0001);
                servoST.setPosition(servoST.getPosition() - 0.0001);
            }

            if(gamepad1.x) {
                rotire_gheara.setPosition(rotire_gheara.getPosition() + 0.0001);
            }

            if(gamepad1.y) {
                rotire_gheara.setPosition(rotire_gheara.getPosition() - 0.0001);
            }

            telemetry.addData("DR:", servoDR.getPosition());
            telemetry.addData("ST:", servoST.getPosition());
            telemetry.addData("gheara:", rotire_gheara.getPosition());
            telemetry.addData("Timp:", timp.seconds());
            //telemetry.addData("Urcare:", urcare);

            telemetry.update();

        }

    }
}
