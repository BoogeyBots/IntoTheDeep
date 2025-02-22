package org.firstinspires.ftc.teamcode.Teste.Module;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class Test_intake_dezasamblat extends LinearOpMode {
    DcMotorEx motor;
    Servo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor_intake");
        servo = hardwareMap.get(Servo.class, "clapita");

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.right_trigger > 0.1) {
                motor.setPower(gamepad1.right_trigger);
            }

            else if(gamepad1.left_trigger > 0.1) {
                motor.setPower(-gamepad1.left_trigger);
            }

            else motor.setPower(0);

            if(gamepad1.a) {
                servo.setPosition(0);
            }

            if(gamepad1.b) {
                servo.setPosition(0.392);
            }
        }
    }
}
