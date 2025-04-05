package org.firstinspires.ftc.teamcode.Offseason.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.DashboardPoseTracker;
import com.pedropathing.util.Drawing;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Offseason.Module.BratModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.GearShifterModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Config
@Autonomous (name = "Basket albastru")
public class Basket_albastru extends OpMode {
    ExtendoModule extendo;
    IntakeModule intake;
    GlisiereModule glisiere;
    BratModule brat;
    GearShifterModule gearShifter;
    DistanceSensor sensor;
    ColorSensor colorsensor;
    Follower follower;
    Timer pathTimer, actionTimer, opmodeTimer;
    int pathState = 0;
    private PoseUpdater poseUpdater;
    private DashboardPoseTracker dashboardPoseTracker;

    public static double timer1 = 2, timer2 = 0.1, timer3 = 0.4, timer4 = 0.28;

    public static double x_startPose = 8.936, y_startPose = 115, heading_startPose = 3.14;
    public static double x_preload = 15.5, y_preload = 127.2, heading_preload = 135;
    public static double x_preload2 = 15.5, y_preload2 = 127.2, heading_preload2 = 135;
    public static double x_colectare1 = 16, y_colectare1 = 124.2, heading_colectare1 = 168;
    public static double x_colectare2 = 18, y_colectare2 = 132.7, heading_colectare2 = 180;
    public static double x_colectare3 = 20, y_colectare3 = 132.7, heading_colectare3= 207;
    public static double x_submersible = 61, y_submersible = 100, heading_submersible = 90;
    public static double x_control1 = 50, y_control1= 123;
    public static double h1 = 200;

    Pose startPose = new Pose(x_startPose, y_startPose, heading_startPose);
    Pose preload = new Pose(x_preload, y_preload, heading_preload);
    Pose colectare1 = new Pose(x_colectare1, y_colectare1, heading_colectare1);
    Pose colectare2 = new Pose(x_colectare2, y_colectare2, heading_colectare2);
    Pose colectare3 = new Pose(x_colectare3, y_colectare3, heading_colectare3);
    Pose submersible = new Pose(x_submersible, y_submersible, heading_submersible);
    Pose submersible2 = new Pose(x_submersible + 18, y_submersible, heading_submersible);
    Pose control1 = new Pose(x_control1, y_control1);
    Pose control2 = new Pose(x_control1, y_control1 + 10);
    Pose preload2 = new Pose(x_preload2, y_preload2);
    Pose sub = new Pose(x_submersible + 18, y_submersible + 4, heading_submersible);


    private Path scorePreload, rotire;
    private PathChain score, move1, move2, move3, move4, move5, move6, move7, move8, move8_2, move9, move10, move11, move12, move13, move14, move15, moving_submersible, moving_submersible_reverse, miscare_intermediara;
    public void buildPaths() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(preload)));
        scorePreload.setConstantHeadingInterpolation(Math.toRadians(heading_preload));

        //follower.setStartingPose(new Pose(startPose.getX(), startPose.getY(), startPose.getHeading()));

        move1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(preload), new Point(colectare1)))
                .setConstantHeadingInterpolation(Math.toRadians(heading_colectare1))
                .build();

        move2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare1), new Point(preload)))
                .setConstantHeadingInterpolation(Math.toRadians(heading_preload))
                .build();

        move3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(preload), new Point(colectare2)))
                .setConstantHeadingInterpolation(Math.toRadians(heading_colectare2))
                .build();

        move4 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare2), new Point(preload)))
                .setConstantHeadingInterpolation(Math.toRadians(heading_preload))
                .build();

        move5 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(preload), new Point(colectare3)))
                .setConstantHeadingInterpolation(Math.toRadians(h1))
                .build();

        move6 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare3), new Point(preload)))
                .setConstantHeadingInterpolation(Math.toRadians(135))
                .build();

        move7 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(preload), new Point(control1), new Point(submersible)))
                .setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(90))
                .build();

        moving_submersible = follower.pathBuilder()
                .addPath(new BezierLine(new Point(submersible), new Point(submersible2)))
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .build();

        move8 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(submersible), new Point(control1), new Point(preload2)))
                .setConstantHeadingInterpolation(Math.toRadians(135))
                .build();

        move8_2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(submersible2), new Point(control2), new Point(preload2)))
                .setConstantHeadingInterpolation(Math.toRadians(135))
                .build();

        moving_submersible_reverse = follower.pathBuilder()
                .addPath(new BezierLine(new Point(submersible2), new Point(submersible)))
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .build();

        miscare_intermediara = follower.pathBuilder()
                .addPath(new BezierLine(new Point(submersible2), new Point(sub)))
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .build();

    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                brat.close();
                intake.open();
                follower.followPath(scorePreload, true);
                setPathState(1);

                break;

            case 1:
                if(pathTimer.getElapsedTimeSeconds() > 0.05) {
                    glisiere.up();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    brat.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.25) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.55) {
                    acasa();
                    setPathState(2);
                }

                break;

            case 2:
                if(pathTimer.getElapsedTimeSeconds() > 0.05) {
                    follower.followPath(move1, true);
                    setPathState(3);
                }

                break;

            case 3:
                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    extendo.extinde();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.3) {
                    intake.jos();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.45) {
                    intake.trage(1);
                    setPathState(4);
                }

                break;

            case 4:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    retrage_extendo();
                    setPathState(5);
                }

                break;

            case 5:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    follower.followPath(move2, true);
                    setPathState(6);
                }



                break;

            case 6:
                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    intake.trage(0.85);
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.8) {
                    intake.stop();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.85) {
                    intake.open();
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.1) {
                    glisiere.up();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.6) {
                    brat.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.3) {
                    brat.open();
                    intake.scuipa(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.55) {
                    acasa();
                    intake.stop();
                    setPathState(7);
                }

                break;

            case 7:
                if(pathTimer.getElapsedTimeSeconds() > 0.05) {
                    follower.followPath(move3, true);
                    setPathState(8);
                }

                break;

            case 8:

                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    extendo.extinde();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.3) {
                    intake.jos();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.45) {
                    intake.trage(1);
                    setPathState(9);
                }

                break;

            case 9:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    retrage_extendo();
                    setPathState(10);
                }

                break;

            case 10:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    follower.followPath(move4, true);
                    setPathState(11);
                }

                break;

            case 11:
                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    intake.trage(0.85);
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.8) {
                    intake.stop();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.85) {
                    intake.open();
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.1) {
                    glisiere.up();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.6) {
                    brat.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.3) {
                    brat.open();
                    intake.scuipa(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.55) {
                    acasa();
                    intake.stop();
                    setPathState(12);
                }

                break;

            case 12:
                if(pathTimer.getElapsedTimeSeconds() > 0.05) {
                    follower.followPath(move5, true);
                    setPathState(13);
                }

                break;

            case 13:

                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    extendo.extinde();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.3) {
                    intake.jos();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.45) {
                    intake.trage(1);
                    setPathState(14);
                }

                break;

            case 14:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    retrage_extendo();
                    setPathState(15);
                }

                break;

            case 15:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    follower.followPath(move6, true);
                    setPathState(16);
                }

                break;

            case 16:
                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    intake.trage(0.85);
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.8) {
                    intake.stop();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.85) {
                    intake.open();
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.1) {
                    glisiere.up();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.6) {
                    brat.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.3) {
                    brat.open();
                    intake.scuipa(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.55) {
                    acasa();
                    intake.stop();
                    setPathState(17);
                }

                break;

            case 17:
                if(pathTimer.getElapsedTimeSeconds() > 0.05) {
                    follower.followPath(move7, true);
                    follower.setMaxPower(1);
                    setPathState(18);
                }

                break;

            case 18:
                if(pathTimer.getElapsedTimeSeconds() > 2) {
                    extendo.extinde();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2 + timer4) {
                    intake.jos();
                    intake.trage(1);
                    setPathState(19);
                }

                break;


            case 19:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    follower.followPath(moving_submersible, true);
                    setPathState(20);
                }


            case 20:
                /*if(pathTimer.getElapsedTimeSeconds() > 0.0001 && sensor.getDistance(DistanceUnit.CM) < 0.79 && colorsensor.blue() >= 1000 && colorsensor.green() <= 1000 && colorsensor.red() <= 1000) {
                    setPathState(21);
                    //impar -> caz naz
                }

                else if(pathTimer.getElapsedTimeSeconds() > 0.7 && sensor.getDistance(DistanceUnit.CM) < 0.79) {
                    setPathState(22);
                    //par -> caz misto
                }

                else if(pathTimer.getElapsedTimeSeconds() > 4 && sensor.getDistance(DistanceUnit.CM) > 1) {
                    intake.scuipa(1);
                    if(pathTimer.getElapsedTimeSeconds() > 5) {
                        setPathState(18);
                    }
                }
                 */

                if(pathTimer.getElapsedTimeSeconds() > 0.01 && sensor.getDistance(DistanceUnit.CM) < 0.79) {
                    if(colorsensor.red() >= 1000 && colorsensor.green() <= 1000)
                        setPathState(21);

                    else if(pathTimer.getElapsedTimeSeconds() > 0.5 && colorsensor.blue() <= 1000 && colorsensor.green() <= 1000)
                        setPathState(22);
                }

                else if(pathTimer.getElapsedTimeSeconds() > 4 && sensor.getDistance(DistanceUnit.CM) > 1) {
                    intake.scuipa(1);
                    if(pathTimer.getElapsedTimeSeconds() > 5)
                        setPathState(69);
                }

                break;

            case 69:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(miscare_intermediara);
                    setPathState(18);
                }

                break;

            case 21:
                if(pathTimer.getElapsedTimeSeconds() > 0.001) {
                    follower.followPath(moving_submersible, true);
                    setPathState(23);
                }

                break;

            case 23:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    intake.scuipa(1);
                    setPathState(25);
                }

                break;

            case 25:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    follower.followPath(moving_submersible_reverse, true);
                    setPathState(27);
                }

                break;

            case 27:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    intake.trage(1);
                    setPathState(20);
                }

                break;


            case 22:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    intake.sus();
                    intake.scuipa(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.125) {
                    intake.trage(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    retrage_extendo();
                    setPathState(24);
                }

                break;


            case 24:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move8_2, true);
                    follower.setMaxPower(1);
                    setPathState(26);
                }

                break;

            case 26:
                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    intake.trage(0.85);
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.8) {
                    intake.stop();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.85) {
                    intake.open();
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.1) {
                    glisiere.up();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.4) {
                    brat.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 3) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > 3.4) {
                    acasa();
                    setPathState(17);
                }

                break;

        }
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }



    @Override
    public void init() {
        poseUpdater = new PoseUpdater(hardwareMap);

        dashboardPoseTracker = new DashboardPoseTracker(poseUpdater);

        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        actionTimer = new Timer();
        actionTimer.resetTimer();

        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();

        extendo = new ExtendoModule(hardwareMap);
        intake = new IntakeModule(hardwareMap);
        brat = new BratModule(hardwareMap);
        glisiere = new GlisiereModule(hardwareMap);
        gearShifter = new GearShifterModule(hardwareMap);
        sensor = hardwareMap.get(DistanceSensor.class, "sensor");
        colorsensor = hardwareMap.get(ColorSensor.class, "colorsensor");



        intake.init_auto();
        brat.init_auto();
        glisiere.init();
        extendo.init();
        gearShifter.init();
    }

    @Override
    public void loop() {
        poseUpdater.update();
        dashboardPoseTracker.update();

        follower.update();
        autonomousPathUpdate();

        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());


        //telemetry.addData("Distance: ", sensor.getDistance(DistanceUnit.CM));
        telemetry.update();

        Drawing.drawPoseHistory(dashboardPoseTracker, "#4CAF50");
        Drawing.drawRobot(poseUpdater.getPose(), "#4CAF50");
        Drawing.sendPacket();
        glisiere.update();
        extendo.update();
    }

    @Override
    public void init_loop() {
        extendo.update();
    }

    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    public void acasa() {
        brat.colectare();
        glisiere.goDown(-10);
        intake.close();
    }
    public void retrage_extendo() {
        extendo.acasa();
        intake.sus();
    }

    public void colectare2() {
        extendo.extinde();
        intake.jos();
    }
}