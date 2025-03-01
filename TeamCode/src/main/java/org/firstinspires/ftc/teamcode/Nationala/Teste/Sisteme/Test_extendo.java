package org.firstinspires.ftc.teamcode.Nationala.Teste.Sisteme;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Nationala.Module.IntakeModule;

@Config
@TeleOp
public class Test_extendo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor_extendo;
        motor_extendo = hardwareMap.get(DcMotor.class , "motor_extendo");
        motor_extendo.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();

        while (opModeIsActive()) {

            if(gamepad1.right_trigger > 0.001) {
                motor_extendo.setPower(gamepad1.right_trigger);
            }

            else if(gamepad1.left_trigger > 0.001) {
                motor_extendo.setPower(-gamepad1.left_trigger);
            }

            else {
                motor_extendo.setPower(0);
            }


            telemetry.addData("Power:", gamepad1.right_trigger * 0.7);
            telemetry.update();


        }
    }
}
