package org.firstinspires.ftc.teamcode.Meeturi;

import android.hardware.Sensor;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Meeturi.Module.BratModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
@TeleOp
public class TeleOP extends LinearOpMode {
    double mod = 1;
    double poz = - 120;
    DistanceSensor sensor;
    //ColorSensor sensor2;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        BratModule brat = new BratModule(hardwareMap);
        ExtendoModule extendo = new ExtendoModule(hardwareMap);
        GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sensor = hardwareMap.get(DistanceSensor.class, "sensor");
        //sensor2 = hardwareMap.get(ColorSensor.class, "sensor2");

        ElapsedTime gheara = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        boolean inchis = false;
        boolean sample = false;
        boolean extins = false;
        boolean glis = false;

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

            if (gamepad1.right_bumper && !extins) {
                brat.close();
                intake.open();
                gheara.reset();
                inchis = true;
                glis = true;
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
                glis = false;
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

            if (gamepad2.right_bumper && !glis) {
                extendo.extinde();
                intake.close();
                extins = true;
            }

            if (gamepad2.dpad_up) {
                extendo.mediu();
                extins = false;
            }

            if (gamepad2.dpad_left) {
                extendo.putin();
                extins = false;
            }

            if(gamepad2.dpad_right) {
                extendo.mai_mediu();
                extins = false;
            }

//            if(gamepad2.dpad_down) {
//                extendo.nu_mai_vrea();
//            }

            if (gamepad2.left_bumper) {
                extendo.acasa();
                intake.sus();
                extins = false;
            }

            if (gamepad2.x) {
                intake.close();
            }

            if (gamepad2.y) {
                intake.open();
            }

            /*if (gamepad2.dpad_down) {
                intake.jos_specimene();
            }

             */

            if(sensor.getDistance(DistanceUnit.CM) > 3.2) {
                sample = true;
            }

            if (sensor.getDistance(DistanceUnit.CM) < 3 && sample) {
                gamepad1.rumble(600);
                gamepad2.rumble(600);
                sample = false;
            }

            /*if(sensor.getDistance(DistanceUnit.CM) < 1.7) {
                if(sensor2.red() >= 1000 && sensor2.green() >= 1200) {
                    gamepad1.setLedColor(1, 0.7, 0, 500);
                    gamepad2.setLedColor(1, 0.7, 0, 500);
                }

                if(sensor2.blue() >= 800 && sensor2.green() <= 650) {
                    gamepad1.setLedColor(0, 0, 1, 500);
                    gamepad2.setLedColor(0, 0, 1, 500);
                }

                if(sensor2.red() >= 800 && sensor2.green() <= 700) {
                    gamepad1.setLedColor(1, 0, 0, 500);
                    gamepad2.setLedColor(1, 0, 0, 500);
                }
            }

            if(sensor2.red() <= 300 && sensor2.green() <= 300 && sensor2.blue() <= 300) {
                gamepad1.setLedColor(0.3, 0, 1, 1000);
                gamepad2.setLedColor(0.3, 0, 1, 1000);
            }

             */

            if(gamepad2.dpad_down) {
                poz -= 2;
               extendo.poz_custom(poz);
               intake.sus();
               extins = false;
            }

            telemetry.addData("Poz extendo:", poz);
            glisiere.update();
            extendo.update();
            telemetry.update();
        }

    }
}
