package org.firstinspires.ftc.teamcode.Teste.Piese;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp (name = "Test Servo")
public class TestServo extends LinearOpMode {
    public Servo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "rotire_gheara");
        servo.setPosition(0);

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                servo.setPosition(servo.getPosition() + 0.0001);
            }

            if(gamepad1.b) {
                servo.setPosition(servo.getPosition() - 0.0001);
            }

            telemetry.addData("Poziție", servo.getPosition());
            telemetry.update();
        }
    }
}
