package org.firstinspires.ftc.teamcode.Teste.Piese;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class TestMotor extends LinearOpMode {
    public DcMotorEx motor;
    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.right_trigger > 0.1) {
                motor.setPower(gamepad1.right_trigger);
            }

            else if(gamepad1.left_trigger > 0.1) {
                motor.setPower(gamepad1.left_trigger * -1);
            }

            else {
                motor.setPower(0);
            }

            telemetry.addData("Power: ", motor.getPower());
            telemetry.update();
        }
    }
}
