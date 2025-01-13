package org.firstinspires.ftc.teamcode.Meeturi;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Meeturi.Module.BratModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
@TeleOp
public class TeleOP extends LinearOpMode {

    double mod = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        BratModule brat = new BratModule(hardwareMap);
        ExtendoModule extendo = new ExtendoModule(hardwareMap);
        GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ElapsedTime gheara = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        boolean inchis = false;

        brat.init();
        glisiere.init_teleOP();
        intake.init();
        extendo.init();

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.left_trigger > 0.1) {
                mod = 0.5;
            }

            else {
                mod = 1;
            }
            gheara.startTime();
            drive.setWeightedDrivePower(
                    new Pose2d(
                             - gamepad1.left_stick_y * mod,
                             - gamepad1.left_stick_x * mod,
                             - gamepad1.right_stick_x * mod
                    )
            );

            drive.update();

            if(gamepad1.square) {
                brat.colectare_specimen_auto();
            }

            if(gamepad1.triangle) {
                brat.intra();
            }

            if(gamepad1.right_trigger > 0.001) {
                brat.iese();
            }

            if (gamepad1.right_bumper) {
                brat.close();
                intake.open();
                gheara.reset();
                inchis = true;
            }

            if (inchis && gheara.seconds() > 0.077) {
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

            if(gamepad2.dpad_down) {
                extendo.nu_mai_vrea();
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
