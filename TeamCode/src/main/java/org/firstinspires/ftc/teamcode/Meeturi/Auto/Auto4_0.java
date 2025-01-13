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

@Disabled
@Config
@Autonomous
public class Auto4_0 extends OpMode {
    ExtendoModule extendo;
    IntakeModule intake;
    GlisiereModule glisiere;
    BratModule brat;
    Follower follower;
    Timer pathTimer, actionTimer, opmodeTimer;
    int pathState = 0;
    private PoseUpdater poseUpdater;
    private DashboardPoseTracker dashboardPoseTracker;

    public static double timer1 = 1.6, timer2 = 1.8, timer3 = 2, timer4 = 1.3, timer5 = 1.7, timer6 = 2;

    public static double x_startPose = 8.936, y_startPose = 53.9, heading_startPose = 0;
    public static double x_preload = 31, y_preload = 70, heading_preload = 0;
    public static double x_moveSample1_1 = 58, y_moveSample1_1 = 29, heading_moveSample1_1 = 0;
    public static double x_controlPoint1 = 30.73, y_controlPoint1 = 12.05, heading_controlPoint1 = 0;
    public static double x_controlPoint2 = 45, y_controlPoint2 = 50.4, heading_controlPoint2 = 0;
    public static double x_moveSample1_2 = 17, y_moveSample1_2 = 29, heading_moveSample1_2 = 90;
    public static double x_moveSample2_1 = 50, y_moveSample2_1 = 22, heading_moveSample2_1 = 90;
    public static double x_controlPoint3 = 63.33, y_controlPoint3 = 45, heading_controlPoint3 = 90;
    public static double x_moveSample2_2 = 17, y_moveSample2_2 = 22, heading_moveSample2_2 = 90;
    public static double x_moveSample3_1 = 47, y_moveSample3_1 = 6.5, heading_moveSample3_1 = 0;
    public static double x_moveSample3_2 = 17, y_moveSample3_2 = 6.5, heading_moveSample3_2 = 0;
    public static double x_controlPoint4 = 63.33, y_controlPoint4 = 30, heading_controlPoint4 = 0;
    public static double x_specimen = 33.7, y_specimen1 = 68, heading_specimen = 0;
    public static double y_specimen2 = 66;
    public static double y_specimen3 = 64;
    public static double y_specimen4 = 62;
    public static double x_controlPoint5 = 20, y_controlPoint5 = 29, heading_controlPoint5 = 0;
    public static double x_controlPoint6 = 41.4, y_controlPoint6 = 27.5, heading_controlPoint6 = 0;
    public static double x_controlPoint7 = 18, y_controlPoint7 = 64, heading_controlPoint7 = 0;
    public static double x_colectare1 = 10.9, y_colectare = 28, heading_colectare = 0;
    public static double x_colectare2 = 16;
    public static double x_colectare3 = 17;
    public static double x_colectare4 = 17;
    public static double x_specimen3 = 35;
    public static double x_specimen4 = 36;

    public static double heading;
    public static double velocity;

    Pose startPose = new Pose(x_startPose, y_startPose, heading_startPose);
    Pose preload = new Pose(x_preload, y_preload, heading_preload);
    Pose preload2 = new Pose(x_preload - 10, y_preload, heading_preload);
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
    Pose colectare1 = new Pose(x_colectare1, y_colectare, heading_colectare);
    Pose colectare2 = new Pose(x_colectare2, y_colectare, heading_colectare);
    Pose colectare3 = new Pose(x_colectare3, y_colectare, heading_colectare);
    Pose specimen1 = new Pose(x_specimen, y_specimen1, heading_specimen);
    Pose specimen2 = new Pose(x_specimen, y_specimen2, heading_specimen);
    Pose specimen3 = new Pose(x_specimen3, y_specimen3, heading_specimen);
    Pose specimen4 = new Pose(x_specimen4, y_specimen4, heading_specimen);
    Pose controlPoint5 = new Pose(x_controlPoint5, y_controlPoint5, heading_controlPoint5);
    Pose controlPoint6 = new Pose(x_controlPoint6, y_controlPoint6, heading_controlPoint6);
    Pose controlPoint7 = new Pose(x_controlPoint7, y_controlPoint7, heading_controlPoint7);
    Pose colectare4 = new Pose(x_colectare4, y_colectare, heading_colectare);
    Pose endPose = new Pose(0, 0, 0);

    private Path scorePreload;
    private PathChain move1, move2, move3, move4, move5, move6, move7, move8, move9, move10, move11, move12, move13, move14, move15;
    public void buildPaths() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(preload)));
        scorePreload.setConstantHeadingInterpolation(0);

        /*move1 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(preload), new Point(controlPoint1), new Point(controlPoint2), new Point(moveSample1_1)))
                .setConstantHeadingInterpolation(0)
                .build();


         */
        move1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(preload), new Point(preload2)))
                .setConstantHeadingInterpolation(3.14)
                .build();


        move2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample1_1), new Point(moveSample1_2)))
                .setConstantHeadingInterpolation(3.14)
                .build();

        move3 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(moveSample1_2), new Point(controlPoint3), new Point(moveSample2_1)))
                .setConstantHeadingInterpolation(3.14)
                .build();

        move4 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample2_1), new Point(moveSample2_2)))
                .setConstantHeadingInterpolation(3.14)
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
                .addPath(new BezierCurve(new Point(moveSample3_2), new Point(controlPoint5), new Point(colectare1)))
                .setConstantHeadingInterpolation(0)
                .setPathEndVelocityConstraint(velocity)
                .build();

        move8 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare1), new Point(controlPoint7), new Point(specimen1)))
                .setConstantHeadingInterpolation(0)
                .build();

        move9 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen1), new Point(controlPoint6), new Point(colectare2)))
                .setConstantHeadingInterpolation(0)
                .setPathEndVelocityConstraint(velocity)
                .build();

        move10 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare2), new Point(controlPoint7), new Point(specimen2)))
                .setConstantHeadingInterpolation(0)
                .build();

        move11 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen2), new Point(controlPoint6), new Point(colectare3)))
                .setPathEndVelocityConstraint(velocity)
                .setConstantHeadingInterpolation(0)
                .build();

        move12 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare3), new Point(controlPoint7), new Point(specimen3)))
                .setConstantHeadingInterpolation(0)
                .build();

        move13 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen3), new Point(controlPoint6), new Point(colectare4)))
                .setPathEndVelocityConstraint(velocity)
                .setConstantHeadingInterpolation(0)
                .build();

        move14 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare4), new Point(controlPoint7), new Point(specimen4)))
                .setConstantHeadingInterpolation(0)
                .build();

        move15 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen4), new Point(controlPoint6), new Point(colectare2)))
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
                actionTimer.resetTimer();
                setPathState(2);
                break;

            case 2:
                if(actionTimer.getElapsedTimeSeconds() > 1) {
                    brat.iese();
                }

                if(actionTimer.getElapsedTimeSeconds() > 1.4) {
                    //brat.colectare_specimen_auto();
                    follower.followPath(move1, true);

                }

                if(actionTimer.getElapsedTimeSeconds() > 2.4) {
                    brat.open();
                    setPathState(-1000);
                }
                break;
                //pana aici avem preload
            case 3:
                actionTimer.resetTimer();
                setPathState(4);
                break;

            case 4:
                colectare();
                if(actionTimer.getElapsedTimeSeconds() > 2) { //de modifict timerele la case 4, 6, 8
                    follower.followPath(move2, true);
                    setPathState(5);
                }
                break;

            case 5:
                actionTimer.resetTimer();
                setPathState(6);
                break;
            case 6:
                if(actionTimer.getElapsedTimeSeconds() > 2) {
                    follower.followPath(move3, true);
                    setPathState(7);
                }
                break;

            case 7:
                actionTimer.resetTimer();
                setPathState(8);
                break;

            case 8:
                if(actionTimer.getElapsedTimeSeconds() > 2) {
                    follower.followPath(move4, true);
                    setPathState(9);
                }
                break;

            case 9:
                actionTimer.resetTimer();
                setPathState(10);
                break;

            case 10:
                if(actionTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move5, true);
                    setPathState(11);
                }
                break;

            case 11:
                actionTimer.resetTimer();
                setPathState(12);
                break;

            case 12:
                if(actionTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move6, true);
                    setPathState(13);
                }
                break;

            case 13:
                actionTimer.resetTimer();
                setPathState(14);
                break;

            case 14:
                if(actionTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move7, true);
                    setPathState(15);
                }
                break;

            case 15:
                actionTimer.resetTimer();
                setPathState(16);
                break;

            case 16:
                if(actionTimer.getElapsedTimeSeconds() > timer1) {
                    brat.close();
                }

                if(actionTimer.getElapsedTimeSeconds() > timer2) {
                    follower.followPath(move8, true);
                    setPathState(17);
                }
                break;

            case 17:
                actionTimer.resetTimer();
                brat.intra();
                setPathState(18);
                break;

            case 18:
                if(actionTimer.getElapsedTimeSeconds() > timer5) {
                    brat.iese();
                }

                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.4)) {
                    follower.followPath(move9);
                }

                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.65)) { //0.75
                    brat.open();
                }
                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.8)) { //0.9
                    colectare();
                    setPathState(19);
                }
                break;

            case 19:
                actionTimer.resetTimer();
                setPathState(20);
                break;

            case 20:
                if(actionTimer.getElapsedTimeSeconds() > timer1) {
                    brat.close();
                }

                if(actionTimer.getElapsedTimeSeconds() > timer2) {
                    follower.followPath(move10, true);
                    setPathState(21);
                }
                break;

            case 21:
                actionTimer.resetTimer();
                brat.intra();
                setPathState(22);
                break;

            case 22:
                if(actionTimer.getElapsedTimeSeconds() > timer5) {
                    brat.iese();
                }

                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.4)) {
                    follower.followPath(move11);
                }

                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.65)) {
                    brat.open();
                }
                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.8)) {
                    colectare();
                    setPathState(23);
                }
                break;

            case 23:
                actionTimer.resetTimer();
                setPathState(24);
                break;

            case 24:
                if(actionTimer.getElapsedTimeSeconds() > timer1) {
                    brat.close();
                }

                if(actionTimer.getElapsedTimeSeconds() > timer2) {
                    follower.followPath(move12, true);
                    setPathState(25);
                }
                break;

            case 25:
                actionTimer.resetTimer();
                brat.intra();
                setPathState(26);
                break;

            case 26:
                if(actionTimer.getElapsedTimeSeconds() > timer5) {
                    brat.iese();
                }

                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.4)) {
                    follower.followPath(move13);
                }

                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.65)) {
                    brat.open();
                }
                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.8)) {
                    colectare();
                    setPathState(27);
                }
                break;

            case 27:
                actionTimer.resetTimer();
                setPathState(28);
                break;

            case 28:
                if(actionTimer.getElapsedTimeSeconds() > timer1) {
                    brat.close();
                }

                if(actionTimer.getElapsedTimeSeconds() > timer2) {
                    follower.followPath(move14, true);
                    setPathState(29);
                }
                break;

            case 29:
                actionTimer.resetTimer();
                brat.intra();
                setPathState(30);
                break;

            case 30:
                if(actionTimer.getElapsedTimeSeconds() > timer5) {
                    brat.iese();
                }

                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.4)) {
                    follower.followPath(move15);
                }

                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.65)) {
                    brat.open();
                }
                if(actionTimer.getElapsedTimeSeconds() > (timer5 + 0.8)) {
                    colectare();
                    setPathState(-1);
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
        brat.colectare_specimen_auto();
        glisiere.goDown();
        brat.open();
    }
}

