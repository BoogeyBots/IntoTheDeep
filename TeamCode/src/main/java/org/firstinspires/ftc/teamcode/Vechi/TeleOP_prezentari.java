package org.firstinspires.ftc.teamcode.Vechi;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

@TeleOp(name = "Robot școli")
public class TeleOP_prezentari extends LinearOpMode {
    private Servo aripa_dreapta, aripa_stanga;

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        aripa_dreapta = hardwareMap.get(Servo.class, "aripa_dreapta");
        aripa_stanga = hardwareMap.get(Servo.class, "aripa_stanga");

        aripa_dreapta.setDirection(Servo.Direction.REVERSE);

        aripa_dreapta.setPosition(0.8128);
        aripa_stanga.setPosition(0);

        waitForStart();

        while (opModeIsActive()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.left_stick_y,
                            - gamepad1.left_stick_x * 0.5,
                            gamepad1.right_stick_x
                    )
            );


            drive.update();

            if(gamepad1.right_trigger > 0.01) {
                aripa_dreapta.setPosition(1);
                aripa_stanga.setPosition(0.3067);
            }

            if(gamepad1.left_trigger > 0.01) {
                aripa_dreapta.setPosition(0.8128);
                aripa_stanga.setPosition(0);
            }

            if(gamepad1.right_bumper) {
                aripa_dreapta.setPosition(1);
            }

            if(gamepad1.left_bumper) {
                aripa_dreapta.setPosition(0.8128);
            }
        }
    }
}
