package org.firstinspires.ftc.teamcode.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@TeleOp
public class Test_intakeNicunebun extends LinearOpMode {
    DcMotorEx motor;
    DistanceSensor sensor;
    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor_intake");
        sensor = hardwareMap.get(DistanceSensor.class, "sensor");

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.right_trigger > 0.1 && sensor.getDistance(DistanceUnit.CM) > 4) {
                motor.setPower(gamepad1.right_trigger);
            }

            else if(gamepad1.left_trigger > 0.1) {
                motor.setPower(-gamepad1.left_trigger);
            }

            else {
                motor.setPower(0);
            }


        }

    }
}
