package org.firstinspires.ftc.teamcode.Teste.Piese;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp (name = "Test Servo")
public class TestServo extends LinearOpMode {
    public Servo servo;
    public static double poz = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "rotire_gheara");
        servo.setPosition(0);

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                servo.setPosition(poz);
            }

            telemetry.addData("Poziție", servo.getPosition());
            telemetry.update();
        }
    }
}
