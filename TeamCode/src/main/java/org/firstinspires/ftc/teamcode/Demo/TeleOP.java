package org.firstinspires.ftc.teamcode.Demo;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Demo.Module.BratModule;
import org.firstinspires.ftc.teamcode.Demo.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Demo.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Demo.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
@TeleOp
public class TeleOP extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        //BratModule brat = new BratModule(hardwareMap);
        ExtendoModule extendo = new ExtendoModule(hardwareMap);
        //GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ElapsedTime timp = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        //brat.init();
        //glisiere.init();
        intake.init();
        extendo.init();

        waitForStart();

        while (opModeIsActive()) {
            timp.startTime();
            drive.setWeightedDrivePower(
                    new Pose2d(
                             - gamepad1.left_stick_y,
                             - gamepad1.left_stick_x,
                             - gamepad1.right_stick_x
                    )
            );

            drive.update();

            if(gamepad1.right_trigger > 0.01) {

                intake.trage(gamepad1.right_trigger * 0.5);
            }

            else if(gamepad1.left_trigger > 0.01) {
                intake.scuipa(gamepad1.left_trigger * 0.5);
            }

            else {
                intake.stop();
            }

            if(gamepad1.a) {
                intake.sus();
            }

            if(gamepad1.b) {
                intake.jos();
            }

            if(gamepad1.right_bumper) {
                extendo.extinde();
            }

            if(gamepad1.left_bumper) {
                extendo.acasa();
            }





        }

    }
}
