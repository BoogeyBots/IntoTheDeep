package org.firstinspires.ftc.teamcode.Meeturi;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Meeturi.Module.BratModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@TeleOp
public class TeleOP_pedro extends LinearOpMode {

    double mod = 1;
    private Follower follower;
    private final Pose startPose = new Pose(0,0,0);
    @Override
    public void runOpMode() throws InterruptedException {
        //SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        BratModule brat = new BratModule(hardwareMap);
        ExtendoModule extendo = new ExtendoModule(hardwareMap);
        GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);


        ElapsedTime gheara = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        boolean inchis = false;

        brat.init();
        glisiere.init();
        intake.init();
        extendo.init();

        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);

        waitForStart();

        follower.startTeleopDrive();

        while (opModeIsActive()) {
            follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);
            follower.update();
            gheara.startTime();

            if (gamepad1.right_bumper) {
                brat.close();
                intake.open();
                gheara.reset();
                inchis = true;
            }

            if (inchis && gheara.seconds() > 0.5) {
                brat.basket();
                glisiere.basket();
                inchis = false;
            }

            if (gamepad1.a) {
                brat.open();
            }

            if (gamepad1.b) {
                brat.close();
            }

            if (gamepad1.left_bumper) {
                brat.colectare();
                glisiere.goDown();
                intake.close();
            }

            if(gamepad1.dpad_left) {
                glisiere.hang();
            }

            if(gamepad1.dpad_down) {
                brat.gheara_jos();
            }


            if (gamepad2.a) {
                intake.jos();
            }

            if (gamepad2.b) {
                intake.sus();
            }

            if (gamepad2.right_trigger > 0.01) {
                intake.trage(gamepad2.right_trigger);
            }

            else if (gamepad2.left_trigger > 0.01) {
                intake.scuipa(gamepad2.left_trigger);
            }

            else {
                intake.stop();
            }

            if (gamepad2.right_bumper) {
                extendo.extinde();
                intake.close();
            }

            if (gamepad2.dpad_up) {
                extendo.mediu();
            }

            if (gamepad2.dpad_left) {
                extendo.putin();
            }

            if(gamepad2.dpad_right) {
                extendo.mai_mediu();
            }

            if (gamepad2.left_bumper) {
                extendo.acasa();
                intake.sus();
            }

            if (gamepad2.x) {
                intake.close();
            }

            if (gamepad2.y) {
                intake.open();
            }

            glisiere.update();
            extendo.update();
        }

    }

}
