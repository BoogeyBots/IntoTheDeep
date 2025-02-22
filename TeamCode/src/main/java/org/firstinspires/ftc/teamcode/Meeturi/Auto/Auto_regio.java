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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Meeturi.Module.BratModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;


@Config
@Autonomous (name = "Misule, pe asta il selectezi pentru 5 + 0")
public class Auto_regio extends OpMode {
    ExtendoModule extendo;
    IntakeModule intake;
    GlisiereModule glisiere;
    BratModule brat;
    Follower follower;
    Timer pathTimer, actionTimer, opmodeTimer;
    int pathState = 0;
    private PoseUpdater poseUpdater;
    private DashboardPoseTracker dashboardPoseTracker;

    public static double timer1 = 1.8, timer2 = 0.5, timer3 = 0.5, timer4 = 0.4, timer5 = 1.7, timer6 = 2;
    public static double pow = 0.6;

    public static double x_startPose = 8.936, y_startPose = 53.9, heading_startPose = 0;
    public static double x_preload = 36, y_preload = 70, heading_preload = 0;
    public static double x_preload2 = 29, y_preload2 = 70, heading_preload2 = 0;
    public static double x_moveSample1_1 = 57, y_moveSample1_1 = 25.5, heading_moveSample1_1 = 0;
    public static double x_controlPoint1 = 30.73, y_controlPoint1 = 12.05, heading_controlPoint1 = 0;
    public static double x_controlPoint2 = 45, y_controlPoint2 = 50.4, heading_controlPoint2 = 0;
    public static double x_moveSample1_2 = 17, y_moveSample1_2 = 25.5, heading_moveSample1_2 = 0;
    public static double x_moveSample2_1 = 56, y_moveSample2_1 = 15, heading_moveSample2_1 = 0;
    public static double x_controlPoint3 = 59, y_controlPoint3 = 30, heading_controlPoint3 = 0; //y = 45, 63.33
    public static double x_moveSample2_2 = 21, y_moveSample2_2 = 15, heading_moveSample2_2 = 0;
    public static double x_moveSample3_1 = 56, y_moveSample3_1 = 7.4, heading_moveSample3_1 = 0;
    public static double x_moveSample3_2 = 24, y_moveSample3_2 = 7.4, heading_moveSample3_2 = 0;
    public static double x_controlPoint4 = 59, y_controlPoint4 = 25, heading_controlPoint4 = 0;  //y = 30
    public static double x_colectare1_1 = 24.5, y_colectare1 = 28, heading_colectare = 0;
    public static double x_controlPoint5 = 20, y_controlPoint5 = 29, heading_controlPoint5 = 0;
    public static double x_colectare1_2 = 16.3;
    public static double x_specimen = 38.5, y_specimen1 = 68, heading_specimen = 0;
    public static double x_controlPoint6 = 18, y_controlPoint6 = 64, heading_controlPoint6 = 0;
    public static double x_controlPoint7 = 18, y_controlPoint7 = 64, heading_controlPoint7 = 0;

    Pose startPose = new Pose(x_startPose, y_startPose, heading_startPose);
    Pose preload = new Pose(x_preload, y_preload, heading_preload);
    Pose preload2 = new Pose(x_preload2, y_preload2, heading_preload2);
    Pose moveSample1_1 = new Pose(x_moveSample1_1, y_moveSample1_1, heading_moveSample1_1);
    Pose controlPoint1 = new Pose(x_controlPoint1, y_controlPoint1, heading_controlPoint1);
    Pose controlPoint2 = new Pose(x_controlPoint2, y_controlPoint2, heading_controlPoint2);
    Pose moveSample1_2 = new Pose(x_moveSample1_2, y_moveSample1_2, heading_moveSample1_2);
    Pose moveSample2_1 = new Pose(x_moveSample2_1, y_moveSample2_1, heading_moveSample2_1);
    Pose controlPoint3 = new Pose(x_controlPoint3, y_controlPoint3, heading_controlPoint3);
    Pose moveSample2_2 = new Pose(x_moveSample2_2, y_moveSample2_2, heading_moveSample2_2);
    Pose moveSample3_1 = new Pose(x_moveSample3_1, y_moveSample3_1, heading_moveSample3_1);
    Pose moveSample3_2 = new Pose(x_moveSample3_2, y_moveSample3_2, heading_moveSample3_2);
    Pose controlPoint4 = new Pose(x_controlPoint4, y_controlPoint4, heading_controlPoint4);
    Pose colectare1_1 = new Pose(x_colectare1_1, y_colectare1, heading_colectare);
    Pose controlPoint5 = new Pose(x_controlPoint5, y_controlPoint5, heading_controlPoint5);
    Pose colectare1_2 = new Pose(x_colectare1_2, y_colectare1, heading_colectare);
    Pose specimen1 = new Pose(x_specimen, y_specimen1, heading_specimen);
    Pose controlPoint6 = new Pose(x_controlPoint6, y_controlPoint6, heading_controlPoint6);
    Pose controlPoint7 = new Pose(x_controlPoint7, y_controlPoint7, heading_controlPoint7);
    Pose specimen2 = new Pose(x_specimen + 3, y_specimen1 - 2, heading_specimen);
    Pose specimen3 = new Pose(x_specimen + 3, y_specimen1 - 4, heading_specimen);


    private Path scorePreload;
    private PathChain move0, move1, move2, move3, move4, move5, move6, move7, move8, move9, move10, move11, move12, move13, move14, move15;
    public void buildPaths() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(preload)));
        scorePreload.setConstantHeadingInterpolation(0);

        move0 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(preload), new Point(preload2)))
                .setConstantHeadingInterpolation(0)
                .build();

        move1 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(preload2), new Point(controlPoint1), new Point(controlPoint2), new Point(moveSample1_1)))
                .setConstantHeadingInterpolation(0)
                .build();

        move2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample1_1), new Point(moveSample1_2)))
                .setConstantHeadingInterpolation(0)
                .build();

        move3 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(moveSample1_2), new Point(controlPoint3), new Point(moveSample2_1)))
                .setConstantHeadingInterpolation(0)
                .build();

        move4 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample2_1), new Point(moveSample2_2)))
                .setConstantHeadingInterpolation(0)
                .build();

        move5 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(moveSample2_2), new Point(controlPoint4), new Point(moveSample3_1)))
                .setConstantHeadingInterpolation(0)
                .build();

        move6 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample3_1), new Point(moveSample3_2)))
                .setConstantHeadingInterpolation(0)
                .build();

        move7 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(moveSample3_2), new Point(controlPoint5), new Point(colectare1_1)))
                .setConstantHeadingInterpolation(0)
                .build();

        move8 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare1_1), new Point(colectare1_2)))
                .setConstantHeadingInterpolation(0)
                .build();

        move9 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare1_2), new Point(controlPoint6), new Point(specimen1)))
                .setConstantHeadingInterpolation(0)
                .build();

        move10 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen1), new Point(controlPoint7), new Point(colectare1_1)))
                .setConstantHeadingInterpolation(0)
                .build();

        move11 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare1_2), new Point(controlPoint6), new Point(specimen2)))
                .setConstantHeadingInterpolation(0)
                .build();

        move12 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen2), new Point(controlPoint7), new Point(colectare1_1)))
                .setConstantHeadingInterpolation(0)
                .build();

        move13 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare1_2), new Point(controlPoint6), new Point(specimen3)))
                .setConstantHeadingInterpolation(0)
                .build();

        move14 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen3), new Point(controlPoint7), new Point(colectare1_1)))
                .setConstantHeadingInterpolation(0)
                .build();

    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                brat.intra();
                follower.followPath(scorePreload, true);
                setPathState(1);
                break;

            case 1:
                if(pathTimer.getElapsedTimeSeconds() > 1.2) {
                    brat.iese();
                    setPathState(2);
                }
                break;

            case 2:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    follower.followPath(move0, true);
                    setPathState(3);
                }
                break;

            case 3:
                if(pathTimer.getElapsedTimeSeconds() > 0.6) {
                    brat.open();
                    setPathState(4);
                }
                break;

            case 4:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    follower.followPath(move1, true);
                    setPathState(5);
                }
                break;

            case 5:
                if(pathTimer.getElapsedTimeSeconds() > 2.5) {
                    follower.followPath(move2, true);
                    setPathState(6);
                }
                break;

            case 6:
                if(pathTimer.getElapsedTimeSeconds() > 1.15) {
                    colectare();
                    follower.followPath(move3, true);
                    setPathState(7);
                }
                break;

            case 7:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move4, true);
                    setPathState(8);
                }

                break;

            case 8:
                if(pathTimer.getElapsedTimeSeconds() >  1.9) { //2.3
                    follower.followPath(move5, true);
                    setPathState(9);
                }

                break;

            case 9:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move6, true);
                    setPathState(10);
                }

                break;

            case 10:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move7, true);
                    setPathState(11);
                }

                break;

            case 11:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move8, true);
                    follower.setMaxPower(pow);
                    setPathState(12);
                }

                break;

            case 12:
                if(pathTimer.getElapsedTimeSeconds() > 1.2) {
                    brat.close();
                    setPathState(13);
                }

                break;

            case 13:
                if(pathTimer.getElapsedTimeSeconds() > 0.2) {
                    brat.intra();
                    follower.followPath(move9, true);
                    follower.setMaxPower(1);
                    setPathState(14);
                }

                break;

            case 14:
                if(pathTimer.getElapsedTimeSeconds() > 1.8) {
                    brat.iese();
                    setPathState(15);
                }

                break;

            case 15:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    follower.followPath(move10, true);
                    setPathState(16);
                }

                break;

            case 16:
                if(pathTimer.getElapsedTimeSeconds() > 0.7) {
                    colectare();
                    setPathState(17);
                }

                break;

            case 17:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move8, true);
                    follower.setMaxPower(pow);
                    setPathState(18);
                }

                break;

            case 18:
                if(pathTimer.getElapsedTimeSeconds() > 1.4) {
                    brat.close();
                    setPathState(19);
                }

                break;

            case 19:
                if(pathTimer.getElapsedTimeSeconds() > 0.4) {
                    brat.intra();
                    follower.followPath(move11, true);
                    follower.setMaxPower(1);
                    setPathState(20);
                }

                break;

            case 20:
                if(pathTimer.getElapsedTimeSeconds() > 1.8) {
                    brat.iese();
                    setPathState(21);
                }

                break;

            case 21:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    follower.followPath(move12, true);
                    setPathState(22);
                }

                break;

            case 22:
                if(pathTimer.getElapsedTimeSeconds() > 0.4) {
                    colectare();
                    setPathState(23);
                }

                break;

            case 23:
                if(pathTimer.getElapsedTimeSeconds() > 1.8) {
                    follower.followPath(move8, true);
                    follower.setMaxPower(pow);
                    setPathState(24);
                }

                break;

            case 24:
                if(pathTimer.getElapsedTimeSeconds() > 0.7) {
                    brat.close();
                    setPathState(25);
                }

                break;

            case 25:
                if(pathTimer.getElapsedTimeSeconds() > 0.4) {
                    brat.intra();
                    follower.followPath(move13, true);
                    follower.setMaxPower(1);
                    setPathState(26);
                }

                break;

            case 26:
                if(pathTimer.getElapsedTimeSeconds() > 1.8) {
                    brat.iese();
                    setPathState(27);
                }

                break;

            case 27:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    follower.followPath(move14, true);
                    setPathState(28);
                }

                break;

            case 28:
                if(pathTimer.getElapsedTimeSeconds() > 0.4) {
                    colectare();
                    setPathState(29);
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

    public void colectare() {
        brat.colectare_specimen_auto_competitie();
        brat.open();
    }
}