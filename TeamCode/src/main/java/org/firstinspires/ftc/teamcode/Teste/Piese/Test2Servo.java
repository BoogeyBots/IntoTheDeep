package org.firstinspires.ftc.teamcode.Teste.Piese;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Testăm 2 servo-uri cică")
public class Test2Servo extends LinearOpMode {
    private Servo servo1, servo2;
    private double modifier = 0.0001;
    @Override
    public void runOpMode() throws InterruptedException {
        servo1 = hardwareMap.servo.get("servoDR");
        servo2 = hardwareMap.servo.get("servoST");

        servo1.setPosition(0);
        servo2.setPosition(0);     //0.5789

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                servo1.setPosition(servo1.getPosition() + modifier);
                servo2.setPosition(servo2.getPosition() + modifier);

            }

            if(gamepad1.b) {
                servo1.setPosition(servo1.getPosition() - modifier);
                servo2.setPosition(servo2.getPosition() - modifier);
            }

            /*if(gamepad1.x) {
                servo2.setPosition(servo2.getPosition() + modifier);
            }

            if(gamepad1.y) {
                servo2.setPosition(servo2.getPosition() - modifier);
            }

             */

            telemetry.addData("Servo1: ", servo1.getPosition());
            telemetry.addData("Servo2: ", servo2.getPosition());
            telemetry.addData("Modifier: ", modifier);
            telemetry.update();
        }

    }
}
