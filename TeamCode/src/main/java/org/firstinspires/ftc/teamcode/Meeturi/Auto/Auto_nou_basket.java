package org.firstinspires.ftc.teamcode.Meeturi.Auto;

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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Meeturi.Module.BratModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
import org.opencv.core.Mat;

@Config
@Autonomous (name = "Misule, vezi că ăsta e de basket")
public class Auto_nou_basket extends OpMode {
    ExtendoModule extendo;
    IntakeModule intake;
    GlisiereModule glisiere;
    BratModule brat;
    Follower follower;
    Timer pathTimer, actionTimer, opmodeTimer;
    int pathState = 0;
    private PoseUpdater poseUpdater;
    private DashboardPoseTracker dashboardPoseTracker;

    public static double timer1 = 1.2, timer2 = 0.5, timer3 = 1.5, timer4 = 8.2;

    public static double x_startPose = 8.936, y_startPose = 115, heading_startPose = 3.14;
    public static double x_preload = 25, y_preload = 122, heading_preload = 135;
    public static double x_colectare1 = 27, y_colectare1 = 122, heading_colectare1 = 180;
    public static double x_colectare2 = 27, y_colectare2 = 131, heading_colectare2 = 180;
    public static double h1 = 223;

    Pose startPose = new Pose(x_startPose, y_startPose, heading_startPose);
    Pose preload = new Pose(x_preload, y_preload, heading_preload);
    Pose colectare1 = new Pose(x_colectare1, y_colectare1, heading_colectare1);
    Pose colectare2 = new Pose(x_colectare2, y_colectare2, heading_colectare2);


    private Path scorePreload, rotire;
    private PathChain score, move1, move2, move3, move4, move5, move6, move7, move8, move9, move10, move11, move12, move13, move14, move15;
    public void buildPaths() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(preload)));
        scorePreload.setConstantHeadingInterpolation(Math.toRadians(heading_preload));

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
                .addPath(new BezierPoint(new Point(preload)))
                .setConstantHeadingInterpolation(Math.toRadians(h1))
                .build();

        move6 = follower.pathBuilder()
                .addPath(new BezierPoint(new Point(preload)))
                .setConstantHeadingInterpolation(Math.toRadians(135))
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
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    brat.basket();
                    glisiere.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.3) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.6) {
                    acasa();
                    setPathState(2);
                }

                break;

            case 2:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move1, true);
                    setPathState(3);
                }

                break;

            case 3:
                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    extendo.mai_mediu();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.2) {
                    intake.jos();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.2) {
                    intake.trage(1);
                    setPathState(4);
                }

                break;

            case 4:
                if(pathTimer.getElapsedTimeSeconds() > 0.7) {
                    retrage_extendo();
                    setPathState(5);
                }

                break;

            case 5:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    follower.followPath(move2, true);
                    setPathState(6);
                }

                break;

            case 6:
                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    intake.stop();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.3) {
                    intake.open();
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.95) {
                    brat.basket();
                    glisiere.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.65) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.95) {
                    acasa();
                    setPathState(7);
                }

                break;

            case 7:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move3, true);
                    setPathState(8);
                }

                break;

            case 8:

                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    extendo.mai_mediu();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.2) {
                    intake.jos();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.2) {
                    intake.trage(1);
                    setPathState(9);
                }

                break;

            case 9:
                if(pathTimer.getElapsedTimeSeconds() > 0.7) {
                    retrage_extendo();
                    setPathState(10);
                }

                break;

            case 10:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    follower.followPath(move4, true);
                    setPathState(11);
                }

                break;

            case 11:
                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    intake.stop();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.23) {
                    intake.open();
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.95) {
                    brat.basket();
                    glisiere.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.65) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.95) {
                    acasa();
                    setPathState(12);
                }

                break;

            case 12:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move5, true);
                    setPathState(13);
                }

                break;

            case 13:

                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    extendo.extinde();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.2) {
                    intake.jos();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.2) {
                    intake.trage(1);
                    setPathState(14);
                }

                break;

            case 14:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    retrage_extendo();
                    setPathState(15);
                }

                break;

            case 15:
                if(pathTimer.getElapsedTimeSeconds() > timer1) {
                    follower.followPath(move6, true);
                    setPathState(16);
                }

                break;

            case 16:
                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    intake.stop();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.23) {
                    intake.open();
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.95) {
                    brat.basket();
                    glisiere.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.65) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.95) {
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


        intake.init();
        brat.init_auto();
        glisiere.init();
        extendo.init();
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

    public void acasa() {
        brat.colectare();
        glisiere.goDown();
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

