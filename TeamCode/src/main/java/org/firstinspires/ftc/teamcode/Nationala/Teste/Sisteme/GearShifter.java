package org.firstinspires.ftc.teamcode.Nationala.Teste.Sisteme;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp
public class GearShifter extends LinearOpMode {
    public static double poz = 0.29;
    Servo gear_right, gear_left;
    @Override
    public void runOpMode() throws InterruptedException {
        gear_right = hardwareMap.get(Servo.class, "gear_right");
        gear_left = hardwareMap.get(Servo.class, "gear_left");

        gear_right.setPosition(0.05);
        gear_left.setPosition(0.05);

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                gear_right.setPosition(poz);
                gear_left.setPosition(poz);
            }
        }
    }
}
