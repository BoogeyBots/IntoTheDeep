package org.firstinspires.ftc.teamcode.Offseason;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Offseason.Module.BratModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.GearShifterModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

@TeleOp
public class TeleOP_dubios extends LinearOpMode {
    DistanceSensor sensor;
    int poz = -20;
    @Override
    public void runOpMode() throws InterruptedException {
        GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        GearShifterModule gearShiter = new GearShifterModule(hardwareMap);
        ExtendoModule extendo = new ExtendoModule(hardwareMap);
        BratModule brat = new BratModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        glisiere.init_teleOP();
        gearShiter.init();
        extendo.init_teleOP();
        brat.init();
        intake.init();

        sensor = hardwareMap.get(DistanceSensor.class, "sensor");

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ElapsedTime gheara = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        ElapsedTime gear = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        ElapsedTime EndGame = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        ElapsedTime timer_specimene = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        boolean inchis = false;
        boolean bool_gear = false;
        boolean specimene = false;
        boolean specimene2 = false;
        boolean sample = false;

        int state = 1;


        waitForStart();

        while (opModeIsActive()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.left_stick_y,
                            gamepad1.left_stick_x,
                            - gamepad1.right_stick_x
                    )
            );

            drive.update();

            gheara.startTime();
            gear.startTime();
            EndGame.startTime();
            timer_specimene.startTime();

            if(gamepad1.right_bumper) {
                brat.close();
                intake.open();
                gheara.reset();
                inchis = true;
                poz = glisiere.encoder_DR() - 5;
                if(poz > -5) {
                    poz = -30;
                }
            }

            if (inchis && gheara.seconds() > 0.25) {
                brat.basketup();
                glisiere.up2();
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
                glisiere.goDown(poz);
                sample = false;
                //glisiere.goDown(0);
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
                intake.scuipa(gamepad2.left_trigger * 0.7);
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


            if(gamepad2.x && EndGame.seconds() > 100) {
                gearShiter.torque();
                gear.reset();
                bool_gear = true;
            }

            if(gear.seconds() > 3.5 && bool_gear) {
                gamepad1.rumble(400);
                gamepad2.rumble(400);
                bool_gear = false;
            }

            if(gamepad2.y) {
                glisiere.hang();
            }

            if(gamepad1.square) {
                brat.colectare_specimene();
            }

            if(gamepad1.triangle && state == 1) {
                timer_specimene.reset();
                glisiere.specimene();
                brat.specimene();
                specimene2 = true;
            }

            if(timer_specimene.seconds() > 2 && specimene2) {
                state = 2;
                specimene2 = false;
            }

            if(gamepad1.triangle && state == 2) {
                glisiere.goDown(0);
                timer_specimene.reset();
                specimene = true;
            }

            if(specimene && timer_specimene.seconds() > 0.15) {
                brat.open();
                specimene = false;
                state = 1;
            }

            if(gamepad1.dpad_up) {
                brat.basket_nasol();
            }

            if(gamepad1.dpad_down) {
                brat.gheara_orizontala();
            }

            if(sensor.getDistance(DistanceUnit.CM) < 1 && !sample) {
                gamepad1.rumble(500);
                gamepad2.rumble(500);
                sample = true;
            }

            /*if(sensor.getDistance(DistanceUnit.CM) < 0.8 && extendo.poz() <= 0 && transfer.seconds() > 0.4) {
                intake.stop();
                brat.close();
                intake.open();
                gheara.reset();
                inchis = true;
                poz = glisiere.encoder_DR() - 5;
                if(poz > -3) {
                    if(EndGame.seconds() < 60) {
                        poz = -35;
                    }

                    else if(EndGame.seconds() < 90) {
                        poz = - 45;
                    }

                    else {
                        poz = -55;
                    }
                }
            }

             */


            telemetry.addData("Distance:", sensor.getDistance(DistanceUnit.CM));
            telemetry.addData("MotorDR: ", glisiere.encoder_DR());
            telemetry.addData("MotorST: ", glisiere.encoder_ST());
            telemetry.addData("Poz:", poz);
            telemetry.addLine("------------");
            telemetry.addData("State: ", state);
            telemetry.addData("Timer specimene: ", timer_specimene.seconds());
            telemetry.addLine("------------");
            telemetry.addData("Sample: ", sample);
            telemetry.addData("Distance: ", sensor.getDistance(DistanceUnit.CM));
            telemetry.update();



            extendo.update();
            glisiere.update();
        }


    }
}
