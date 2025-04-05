package org.firstinspires.ftc.teamcode.Offseason.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.BezierPoint;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.DashboardPoseTracker;
import com.pedropathing.util.Drawing;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Offseason.Module.BratModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.GearShifterModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

import dev.frozenmilk.sinister.loading.Pinned;
@Pinned
@Config
@Autonomous
public class Auto5_1 extends OpMode {
    ExtendoModule extendo;
    IntakeModule intake;
    GlisiereModule glisiere;
    BratModule brat;
    GearShifterModule gearShifter;
    Follower follower;
    Timer pathTimer, actionTimer, opmodeTimer;
    int pathState = 0;
    private PoseUpdater poseUpdater;
    private DashboardPoseTracker dashboardPoseTracker;

    public static double timer1 = 1.5, timer2 = 0.01, timer3 = 1.4;

    public static double x_startPose = 9, y_startPose = 65.623, heading_startPose = 3.14;
    public static double x_preload = 40.5, y_preload = 65.623;
    public static double x_controlPoint1 = 18, y_controlPoint1 = 55;
    public static double x_sample1 = 25, y_sample1 = 47;
    public static double x_sample2 = 28.5, y_sample2 = 35;
    public static double x_sample3 = 28, y_sample3 = 30;
    public static double x_int = 25, y_int = 35;
    public static double x_colectare = 16.5, y_colectare = 35;
    public static double x_specimen = 40.8, y_specimen = 68;
    public static double x_csample = 8, y_csample = 58;
    public static double x_basket = 8, y_basket = 123.7;
    public static double heading, h1 = 90;
    Pose startPose = new Pose(x_startPose, y_startPose, heading_startPose);
    Pose preload = new Pose(x_preload, y_preload);
    Pose controlPoint1 = new Pose(x_controlPoint1, y_controlPoint1);
    Pose sample1 = new Pose(x_sample1, y_sample1);
    Pose sample2 = new Pose(x_sample2, y_sample2);
    Pose sample3 = new Pose(x_sample3, y_sample3);
    Pose intermediar = new Pose(x_int, y_int);
    Pose colectare = new Pose(x_colectare, y_colectare);
    Pose specimen1 = new Pose(x_specimen, y_specimen);
    Pose specimen2 = new Pose(x_specimen, y_specimen + 3);
    Pose specimen3 = new Pose(x_specimen, y_specimen + 6);
    Pose specimen4 = new Pose(x_specimen, y_specimen + 9);
    Pose csample = new Pose(x_csample, y_csample);
    Pose basket = new Pose(x_basket, y_basket);
    Pose parcare = new Pose(x_csample, y_csample - 10);


    private Path scorePreload;
    private PathChain move1, move2, move3, move4, move5, move6, move7, move8, move9, move10, move11, move12, move13, move14, move15, move9_2, move11_2, move6_2, move16, move17, move18;
    public void buildPaths() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(preload)));
        scorePreload.setConstantHeadingInterpolation(Math.toRadians(180));

        move1 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(preload), new Point(controlPoint1), new Point(sample1)))
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(130))
                .build();

        move2 = follower.pathBuilder()
                .addPath(new BezierPoint(new Point(sample1)))
                .setConstantHeadingInterpolation(Math.toRadians(65))
                .build();

        move3 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(sample1), new Point(sample2)))
                .setConstantHeadingInterpolation(Math.toRadians(120))
                .build();

        move4 = follower.pathBuilder()
                .addPath(new BezierPoint(new Point(sample2)))
                .setConstantHeadingInterpolation(Math.toRadians(65))
                .build();

        move5 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(sample2), new Point(sample3)))
                .setConstantHeadingInterpolation(Math.toRadians(113))
                .build();

        move6 = follower.pathBuilder()
                .addPath(new BezierPoint(new Point(sample3)))
                .setConstantHeadingInterpolation(Math.toRadians(65))
                .build();

        move7 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(sample3), new Point(intermediar)))
                .setLinearHeadingInterpolation(Math.toRadians(65), Math.toRadians(180))
                .build();

        move8 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(intermediar), new Point(colectare)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move9 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare), new Point(specimen1)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move10 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen1), new Point(intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move11 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare), new Point(specimen2)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move12 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen2), new Point(intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move13 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare), new Point(specimen3)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move14 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen3), new Point(intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move15 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare), new Point(specimen4)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move16 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen4), new Point(csample)))
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))
                .build();

        move17 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(csample), new Point(basket)))
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .build();

        move18 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(basket), new Point(parcare)))
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .build();

    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(scorePreload, true);
                setPathState(1);
                break;

            case 1:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    glisiere.specimene();
                    brat.specimene();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.2) {
                    glisiere.goDown(0);
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.45) {
                    brat.open();
                    intake.close();
                    setPathState(2);
                }

                break;

            case 2:
                if(pathTimer.getElapsedTimeSeconds() > 0) {
                    follower.followPath(move1, true);
                    setPathState(3);
                }
                break;

            case 3:
                if(pathTimer.getElapsedTimeSeconds() > 0.7) {
                    extendo.extinde();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.8) {
                    intake.trage(1);
                    intake.jos();
                    setPathState(4);
                }

                break;

            case 4:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    follower.followPath(move2, true);
                    setPathState(5);
                }

                break;

            case 5:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    intake.intermediar();
                    intake.scuipa(1);
                    setPathState(6);
                }

                break;

            case 6:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    follower.followPath(move3, true);
                    setPathState(7);
                }

                break;

            case 7:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    intake.jos();
                    intake.trage(1);
                    setPathState(8);
                }

                break;

            case 8:
                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    follower.followPath(move4, true);
                    setPathState(9);
                }

                break;

            case 9:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    intake.intermediar();
                    intake.scuipa(1);
                    setPathState(10);
                }

                break;

            case 10:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    follower.followPath(move5, true);
                    setPathState(11);
                }

                break;

            case 11:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    intake.jos();
                    intake.trage(1);
                    setPathState(12);
                }

                break;

            case 12:
                if(pathTimer.getElapsedTimeSeconds() > 0.8) {
                    follower.followPath(move6, true);
                    setPathState(13);
                }

                break;

            case 13:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    intake.intermediar();
                    intake.scuipa(1);
                    setPathState(14);
                }

                break;

            case 14:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    extendo.acasa();
                    intake.sus();
                    brat.colectare_specimene();
                    intake.stop();
                    setPathState(15);
                }

                break;

            case 15:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    follower.followPath(move7);
                    setPathState(16);
                }

                break;

            case 16:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    follower.followPath(move8);
                    setPathState(17);
                }

                break;

            case 17:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.3) {
                    brat.specimene();
                    glisiere.specimene();
                    setPathState(18);
                }

                break;

            case 18:
                if(pathTimer.getElapsedTimeSeconds() > 0) {
                    follower.followPath(move9, true);
                    setPathState(19);
                }

                break;

            case 19:
                if(pathTimer.getElapsedTimeSeconds() > 1.55) {
                    glisiere.goDown(0);
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.8) {
                    brat.open();
                    setPathState(20);
                }

                break;

            case 20:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move10, true);
                    setPathState(21);
                }

                break;

            case 21:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    brat.colectare_specimene();
                    setPathState(22);
                }

                break;

            case 22:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move8, true);
                    setPathState(23);
                }

                break;

            case 23:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    brat.specimene();
                    glisiere.specimene();
                    setPathState(24);
                }

                break;

            case 24:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                follower.followPath(move11, true);
                setPathState(25);
            }

            break;

            case 25:
                if(pathTimer.getElapsedTimeSeconds() > 1.55) {
                    glisiere.goDown(0);
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.8) {
                    brat.open();
                    setPathState(26);
                }

                break;

            case 26:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move12, true);
                    setPathState(27);
                }

                break;

            case 27:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    brat.colectare_specimene();
                    setPathState(28);
                }

                break;

            case 28:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move8, true);
                    setPathState(29);
                }

                break;

            case 29:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    brat.specimene();
                    glisiere.specimene();
                    setPathState(30);
                }

                break;

            case 30:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move13, true);
                    setPathState(31);
                }

                break;

            case 31:
                if(pathTimer.getElapsedTimeSeconds() > 1.55) {
                    glisiere.goDown(0);
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.8) {
                    brat.open();
                    setPathState(32);
                }

                break;

            case 32:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move14, true);
                    setPathState(33);
                }

                break;

            case 33:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    brat.colectare_specimene();
                    setPathState(34);
                }

                break;

            case 34:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move8, true);
                    setPathState(35);
                }

                break;

            case 35:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    brat.specimene();
                    glisiere.specimene();
                    setPathState(36);
                }

                break;

            case 36:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move15, true);
                    setPathState(37);
                }

                break;

            case 37:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    glisiere.goDown(0);
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.8) {
                    brat.open();
                    setPathState(38);
                }

                break;

            case 38:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move16, true);
                    setPathState(39);
                }

                break;

            case 39:
                if(pathTimer.getElapsedTimeSeconds() > 0.45) {
                    extendo.extinde();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.55) {
                    intake.trage(1);
                    intake.jos();
                    setPathState(40);
                }

                break;

            case 40:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    intake.sus();
                    extendo.acasa();
                    brat.colectare();
                    brat.open();
                    setPathState(41);
                }

                break;

            case 41:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move17, true);
                    setPathState(42);
                }

                break;

            case 42:
                if(pathTimer.getElapsedTimeSeconds() > 0.3) {
                    intake.stop();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    intake.open();
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    glisiere.up();
                    setPathState(43);
                }

            case 43:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    brat.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.55) {
                    brat.open();
                    setPathState(44);
                }

                break;

            case 44:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move18, true);
                    setPathState(45);
                }

                break;

            case 45:
                if(pathTimer.getElapsedTimeSeconds() > 0) {
                    extendo.extinde();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    brat.colectare();
                    glisiere.goDown(0);
                    setPathState(46);
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


        intake.init_auto();
        brat.init_specimene();
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

}