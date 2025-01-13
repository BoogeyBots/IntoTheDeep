package org.firstinspires.ftc.teamcode.Meeturi.Auto;

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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Meeturi.Module.BratModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
import org.opencv.core.Mat;

@Disabled
@Config
@Autonomous
public class Basket4_0 extends OpMode {
    ExtendoModule extendo;
    IntakeModule intake;
    GlisiereModule glisiere;
    BratModule brat;
    Follower follower;
    Timer pathTimer, actionTimer, opmodeTimer;
    int pathState = 0;
    private PoseUpdater poseUpdater;
    private DashboardPoseTracker dashboardPoseTracker;

    public static double timer1 = 1.5, timer2 = 1.5, timer3 = 1.5, timer4 = 8.2;

    public static double x_startPose = 6, y_startPose = 111, heading_startPose = 1.57;
    public static double x_colectare1 = 23, y_colectare1 = 126.27, heading_punctare2 = 137;
    public static double x_punctare_real = 23, y_punctare_real = 120;
    public static double x_controlPoint1 = 28, y_controlPoint1 = 130;


    public static double heading, h1 = 233, h2 = 163, heading_colectare1 = 137, heading_punctare_real = 130;

    public static double velocity;

    Pose startPose = new Pose(x_startPose, y_startPose, heading_startPose);
    Pose colectare1 = new Pose(x_colectare1, y_colectare1, heading_colectare1);
    Pose punctare2 = new Pose(x_colectare1, y_colectare1, heading_punctare2);
    Pose punctare_real = new Pose(x_punctare_real, y_punctare_real, heading_punctare_real);
    Pose controlPoint = new Pose(x_controlPoint1, y_controlPoint1, heading);

    private Path scorePreload, rotire;
    private PathChain score, move1, move2, move3, move4, move5, move6, move7, move8, move9, move10, move11, move12, move13, move14, move15;
    public void buildPaths() {
        /*scorePreload = new Path(new BezierLine(new Point(startPose), new Point(punctare)));
        scorePreload.setLinearHeadingInterpolation(heading_startPose, heading_punctare);

        rotire = new Path(new BezierPoint(new Point(punctare)));
        rotire.setConstantHeadingInterpolation(heading_punctare_real);

         */

        move1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(startPose), new Point(colectare1)))
                .setLinearHeadingInterpolation(heading_startPose, Math.toRadians(163))
                .build();

        move2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare1), new Point(punctare_real)))
                .setConstantHeadingInterpolation(Math.toRadians(heading_punctare_real))
                .build();
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                brat.close();
                intake.open();

                if(pathTimer.getElapsedTimeSeconds() > 0.1 + 0.07) {
                    brat.basket();
                    glisiere.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.9) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > 2.2) {
                    acasa();
                    setPathState(1);
                }
                break;
            case 1:
                follower.followPath(move1, true);
                setPathState(2);
                break;

            case 2:
                //follower.holdPoint(new Point(punctare), Math.toRadians(heading_colectare1));
                setPathState(3);

            case 3:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    colectare();
                    intake.trage(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 3.2) {
                    retrage_extendo();
                    setPathState(4);
                }
                break;

            case 4:
                    follower.followPath(move2, true);
                    setPathState(5);
                    break;

            case 5:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    intake.stop();
                    brat.close();
                    intake.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.35 + 0.1 + 0.5) {
                    brat.basket();
                    glisiere.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.2 * 2 + 0.7) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > 1.2 * 2 + 0.9) {
                    acasa();
                    setPathState(6);
                }
                break;

            case 6:
                if(pathTimer.getElapsedTimeSeconds() > timer1) {
                    follower.holdPoint(new Point(punctare_real), Math.toRadians(212));
                    setPathState(7);
                }

            case 7:
                if(pathTimer.getElapsedTimeSeconds() > 2) {
                    colectare();
                    intake.trage(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 3.5) {
                    retrage_extendo();
                    setPathState(8);
                }
                break;

            case 8:
                if(pathTimer.getElapsedTimeSeconds() > timer2) {
                    follower.holdPoint(new Point(punctare_real), Math.toRadians(heading_punctare_real));
                    setPathState(9);
                }

            case 9:
                if(pathTimer.getElapsedTimeSeconds() > timer2 - 0.1) {
                    intake.stop();
                    brat.close();
                    intake.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > timer2 + 0.1) {
                    brat.basket();
                    glisiere.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > timer2 + 1.55) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > timer2 + 2.85) {
                    acasa();
                    setPathState(10);
                }
                break;

            case 10:
                if(pathTimer.getElapsedTimeSeconds() > timer3) {
                    follower.holdPoint(new Point(punctare_real), Math.toRadians(233));
                    setPathState(11);
                }

            case 11:
                if(pathTimer.getElapsedTimeSeconds() > 2) {
                    colectare();
                    intake.trage(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 3.5) {
                    retrage_extendo();
                    setPathState(12);
                }
                break;

            case 12:
                if(pathTimer.getElapsedTimeSeconds() > timer3) {
                    follower.holdPoint(new Point(punctare_real), Math.toRadians(heading_punctare_real));
                    setPathState(13);
                }

            case 13:
                if(pathTimer.getElapsedTimeSeconds() > timer3 - 0.5) {
                    intake.stop();
                    brat.close();
                    intake.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > timer3 + 0.1) {
                    brat.basket();
                    glisiere.basket();
                }

                if(pathTimer.getElapsedTimeSeconds() > timer3 + 1.8) {
                    brat.open();
                }

                if(pathTimer.getElapsedTimeSeconds() > timer3 + 2.8) {
                    acasa();
                    setPathState(14);
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


        intake.init_auto();
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

    public void colectare() {
        extendo.mai_mediu();
        intake.jos();
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

