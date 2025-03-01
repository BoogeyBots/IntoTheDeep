package org.firstinspires.ftc.teamcode.Nationala;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Nationala.Module.BratModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.GearShifterModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
@TeleOp
public class TeleOP extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        GearShifterModule gearShiter = new GearShifterModule(hardwareMap);
        ExtendoModule extendo = new ExtendoModule(hardwareMap);
        BratModule brat = new BratModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        glisiere.init();
        gearShiter.init();
        extendo.init();
        brat.init();
        intake.init();

        ElapsedTime gheara = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        ElapsedTime gear = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        boolean inchis = false;
        boolean bool_gear = false;


        waitForStart();

        while (opModeIsActive()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            - gamepad1.left_stick_y,
                            - gamepad1.left_stick_x,
                            - gamepad1.right_stick_x
                    )
            );

            drive.update();
            gheara.startTime();
            gear.startTime();

            if(gamepad1.right_bumper) {
                brat.close();
                intake.open();
                gheara.reset();
                inchis = true;
            }

            if (inchis && gheara.seconds() > 0.15) {
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

            if (gamepad2.left_bumper) {
                extendo.acasa();
                intake.sus();
            }

            if (gamepad2.dpad_left) {
                extendo.low();
            }

            if (gamepad2.dpad_up) {
                extendo.mediu();
            }

            if (gamepad2.dpad_right) {
                extendo.intermediate();
            }

            if (gamepad2.dpad_down) {
                extendo.high();
            }

            if(gamepad1.dpad_up) {
                glisiere.up();
            }

            if(gamepad2.x) {
                glisiere.goDown();
                gear.reset();
                bool_gear = true;
            }

            if(bool_gear && gear.seconds() > 1) {
                gearShiter.torque();
            }

            if(gamepad2.y) {
                glisiere.hang();
            }

            extendo.update();
            glisiere.update();
        }


    }
}
