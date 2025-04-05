package org.firstinspires.ftc.teamcode.Offseason.Teste;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp
public class Test_servo extends LinearOpMode {
    Servo servo;
    public static double poz;
    @Override
    public void runOpMode() throws InterruptedException {

        servo = hardwareMap.get(Servo.class, "servo_gheara");

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                servo.setPosition(poz);
            }
        }

    }
}
