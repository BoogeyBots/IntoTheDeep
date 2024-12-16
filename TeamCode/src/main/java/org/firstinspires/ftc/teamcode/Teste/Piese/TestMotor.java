package org.firstinspires.ftc.teamcode.Teste.Piese;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@TeleOp
public class TestMotor extends LinearOpMode {
    public DcMotorEx motor;

    public static double power;
    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor_extendo");

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.right_trigger > 0.1) {
                motor.setPower(power * -1);
            }

            else if(gamepad1.left_trigger > 0.1) {
                motor.setPower(power);
            }

            else {
                motor.setPower(0);
            }

            telemetry.addData("Power: ", motor.getPower());
            telemetry.update();
        }
    }
}
