package org.firstinspires.ftc.teamcode.Teste.Piese;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@TeleOp
public class TestMotor extends LinearOpMode {
    public DcMotorEx motor;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor_intake");

        waitForStart();
        //9.9

        while (opModeIsActive()) {
            if(gamepad1.right_trigger > 0.1) {
                motor.setPower(gamepad1.right_trigger);
            }

            else if(gamepad1.left_trigger > 0.1) {
                motor.setPower(-gamepad1.left_trigger);
            }

            else {
                motor.setPower(0);
            }
            /*if(gamepad1.a) {
                motor.setPower(0.6);
            }
            else if(gamepad1.b) {
                motor.setPower(0.7);
            }
             else if(gamepad1.x) {
                motor.setPower(0.5);
            }
            else if(gamepad1.y) {
                motor.setPower(0.8);
            }
            else {
                motor.setPower(0);
            }*/

            telemetry.addData("Power: ", motor.getPower());
            telemetry.update();
        }
    }
}
