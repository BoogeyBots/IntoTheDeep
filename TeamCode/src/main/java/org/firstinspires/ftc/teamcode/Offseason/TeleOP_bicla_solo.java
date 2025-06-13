package org.firstinspires.ftc.teamcode.Offseason;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Offseason.Module.BratModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.GearShifterModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

@TeleOp
public class TeleOP_bicla_solo extends LinearOpMode {

    enum STATE {
        COLECTARE,
        PUNCTARE,
        SPECIMENE
    }

    DistanceSensor sensor;
    double angle = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        GearShifterModule gearShifter = new GearShifterModule(hardwareMap);
        ExtendoModule extendo = new ExtendoModule(hardwareMap);
        BratModule brat = new BratModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        glisiere.init();
        gearShifter.init();
        extendo.init_teleOP();
        brat.init();
        intake.init();

        while (opModeInInit()) {
            glisiere.update();
        }

        sensor = hardwareMap.get(DistanceSensor.class, "sensor");

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ElapsedTime gheara = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        ElapsedTime gear = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        ElapsedTime endGame = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        ElapsedTime switchingTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        boolean inchis = false;
        boolean switchingState = false;
        boolean bool_gear = false;

        STATE mode = STATE.COLECTARE;

        waitForStart();

        while (opModeIsActive()) {

            drive.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.left_stick_y,
                            gamepad1.left_stick_x,
                            -gamepad1.right_stick_x * angle
                    )
            );
            drive.update();

            if (gamepad1.right_bumper && mode == STATE.PUNCTARE) {
                brat.close();
                gheara.reset();
                inchis = true;
            }

            if (inchis && gheara.seconds() > 0.25) {
                glisiere.up();
            }

            if (inchis && gheara.seconds() > 0.55) {
                brat.basket();
                inchis = false;
            }

            if (gamepad1.left_bumper) {
                brat.colectare();
                glisiere.goDown(160);
                extendo.acasa();
                intake.sus();

                switchingTimer.reset();
                switchingState = true;
            }

            if (switchingState && switchingTimer.seconds() > 0.05) {
                mode = (mode == STATE.COLECTARE) ? STATE.PUNCTARE : STATE.COLECTARE;
                switchingState = false;
            }

            if (mode == STATE.COLECTARE) {
                if (gamepad1.right_bumper) {
                    extendo.extinde();
//                    brat.colectare();
//                    glisiere.goDown(190);
                    brat.colectare();
                    glisiere.goDown(160);
                }

                if (gamepad1.a) {
                    intake.jos();
                }

                if (gamepad1.b) {
                    intake.sus();
                }
            }


            if (mode == STATE.PUNCTARE) {
                if (gamepad1.a) {
                    brat.open();
                }
            }


            if (gamepad1.right_trigger > 0.01) {
                intake.trage(gamepad1.right_trigger);
            }

            else if (gamepad1.left_trigger > 0.01) {
                intake.scuipa(gamepad1.left_trigger * 0.7);
            }

            else {
                intake.stop();
            }


            if (gamepad1.dpad_left) extendo.low();
            if (gamepad1.dpad_up) extendo.mediu();
            if (gamepad1.dpad_right) extendo.intermediate();
            if (gamepad1.dpad_down) extendo.high();


            if (gamepad1.square && endGame.seconds() > 100) {
                gearShifter.torque();
                gear.reset();
                bool_gear = true;
            }

            if (gear.seconds() > 3.5 && bool_gear) {
                gamepad1.rumble(400);
                bool_gear = false;
            }


            if (gamepad1.y) {
                glisiere.hang();
            }


            if (glisiere.encoder_DR() > 600 || extendo.getEncoderPosition() > 250) {
                angle = 0.6;
            }

            else {
                angle = 1;
            }


            telemetry.addData("Mod curent", mode);
            telemetry.update();


            extendo.update();
            glisiere.update();
        }
    }
}
