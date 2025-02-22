package org.firstinspires.ftc.teamcode.Teste;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp
public class Test_PTO extends LinearOpMode {
    public static double poz;
    DcMotor motor;
    Servo servo;
    @Override
    public void runOpMode() throws InterruptedException {
       motor = hardwareMap.get(DcMotor.class, "motor");
       servo = hardwareMap.get(Servo.class, "servo");

       waitForStart();

       while(opModeIsActive()) {
           if(gamepad1.right_trigger > 0.01) {
               motor.setPower(gamepad1.right_trigger);
           }

           else motor.setPower(0);

           if(gamepad1.a) {
               servo.setPosition(poz);
           }
       }
    }
}