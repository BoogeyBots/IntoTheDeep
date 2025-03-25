package org.firstinspires.ftc.teamcode.Nationala.Auto;

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

import org.firstinspires.ftc.teamcode.Nationala.Module.BratModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.GearShifterModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.IntakeModule;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Config
@Autonomous
public class Auto5_0 extends OpMode {
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

    public static double timer1 = 1.5, timer2 = 0.7, timer3 = 1, timer4 = 0.9, timer5 = 1, timer6 = 1, timer7 = 1;

    public static double x_startPose = 9, y_startPose = 65.623, heading_startPose = 3.14;
    public static double x_preload = 40.5, y_preload = 65.623;
    public static double x_moveSample1_1 = 54, y_moveSample1_1 = 23;
    public static double x_controlPoint1 = 1.8, y_controlPoint1 = 30.2;
    public static double x_controlPoint2 = 79.1, y_controlPoint2 = 38.54;
    public static double x_moveSample1_2 = 22, y_moveSample1_2 = 23;
    public static double x_moveSample2_1 = 54, y_moveSample2_1 = 15;
    public static double x_controlPoint3 = 60.2, y_controlPoint3 = 29; //63.33, 45
    public static double x_moveSample2_2 = 22, y_moveSample2_2 = 15;
    public static double x_moveSample3_1 = 54, y_moveSample3_1 = 8;
    public static double x_moveSample3_2 = 15.9, y_moveSample3_2 = 8;
    public static double x_controlPoint4 = 63.33, y_controlPoint4 = 30;
    public static double x_specimen = 40.5, y_specimen1 = 69;
    public static double y_specimen2 = 71;
    public static double y_specimen3 = 73;
    public static double y_specimen4 = 75;
    public static double x_controlPoint5 = 30, y_controlPoint5 = 35;
    public static double x_controlPoint6 = 25, y_controlPoint6 = 36;
    public static double x_controlPoint7 = 18, y_controlPoint7 = 64;
    public static double x_colectare = 16.3, y_colectare = 35;
    public static double x_colectare4 = 19;
    public static double x_specimen3 = 35;
    public static double x_specimen4 = 36;

    public static double heading;
    Pose startPose = new Pose(x_startPose, y_startPose, heading_startPose);
    Pose preload = new Pose(x_preload, y_preload);
    Pose moveSample1_1 = new Pose(x_moveSample1_1, y_moveSample1_1);
    Pose controlPoint1 = new Pose(x_controlPoint1, y_controlPoint1);
    Pose controlPoint2 = new Pose(x_controlPoint2, y_controlPoint2);
    Pose moveSample1_2 = new Pose(x_moveSample1_2, y_moveSample1_2);
    Pose moveSample2_1 = new Pose(x_moveSample2_1, y_moveSample2_1);
    Pose controlPoint3 = new Pose(x_controlPoint3, y_controlPoint3);
    Pose moveSample2_2 = new Pose(x_moveSample2_2, y_moveSample2_2);
    Pose moveSample3_1 = new Pose(x_moveSample3_1, y_moveSample3_1);
    Pose moveSample3_2 = new Pose(x_moveSample3_2, y_moveSample3_2);
    Pose moveSample3_2_intermediar = new Pose(x_moveSample3_2 + 6, y_moveSample3_2);
    Pose controlPoint4 = new Pose(x_controlPoint4, y_controlPoint4);
    Pose colectare = new Pose(x_colectare, y_colectare);
    Pose colectare_intermediar = new Pose(22, 35);
    Pose specimen1 = new Pose(x_specimen, y_specimen1);
    Pose specimen1_intermediar = new Pose(x_specimen - 5, y_specimen1 - 5);
    Pose specimen2 = new Pose(x_specimen, y_specimen2);
    Pose specimen2_intermediar = new Pose(x_specimen - 5, y_specimen2 - 5);
    Pose specimen3_intermediar = new Pose(x_specimen - 5, y_specimen3 - 5);
    Pose specimen4_intermediar = new Pose(x_specimen - 5, y_specimen4 - 5);
    Pose specimen3 = new Pose(x_specimen, y_specimen3);
    Pose specimen4 = new Pose(x_specimen, y_specimen4);
    Pose controlPoint5 = new Pose(x_controlPoint5, y_controlPoint5);
    Pose controlPoint6 = new Pose(x_controlPoint6, y_controlPoint6);
    Pose controlPoint7 = new Pose(x_controlPoint7, y_controlPoint7);
    Pose colectare4 = new Pose(x_colectare4, y_colectare);
    Pose intermediar = new Pose(21, 28);

    private Path scorePreload;
    private PathChain move1, move2, move3, move4, move5, move6, move7, move8, move9, move10, move11, move12, move13, move14, move15, move9_2, move11_2, move6_2, move16, move17, move18;
    public void buildPaths() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(preload)));
        scorePreload.setConstantHeadingInterpolation(Math.toRadians(180));

        move1 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(preload), new Point(controlPoint1), new Point(controlPoint2), new Point(moveSample1_1)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();


        move2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample1_1), new Point(moveSample1_2)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move3 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(moveSample1_2), new Point(controlPoint3), new Point(moveSample2_1)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move4 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample2_1), new Point(moveSample2_2)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move5 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(moveSample2_2), new Point(controlPoint4), new Point(moveSample3_1)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move6 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample3_1), new Point(moveSample3_2_intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move6_2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample3_2_intermediar), new Point(moveSample3_2)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move7 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(moveSample3_2), new Point(specimen1_intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move8 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen1_intermediar), new Point(specimen1)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move9 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen1), new Point(colectare_intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move10 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare_intermediar), new Point(colectare)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move11 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare), new Point(specimen2_intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move12 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen2_intermediar), new Point(specimen2)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move13 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen2), new Point(colectare_intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move14 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare), new Point(specimen3_intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move15 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen3_intermediar), new Point(specimen3)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move16 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen3), new Point(colectare_intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move17 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(colectare), new Point(specimen4_intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move18 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimen4_intermediar), new Point(specimen4)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();


        /*move9 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen1), new Point(controlPoint6), new Point(intermediar)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move9_2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen1), new Point(controlPoint6), new Point(colectare1)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move10 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare1), new Point(controlPoint7), new Point(specimen2)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move11 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen2), new Point(controlPoint6), new Point(colectare3)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move12 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare3), new Point(controlPoint7), new Point(specimen3)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move13 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen3), new Point(controlPoint6), new Point(colectare4)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move14 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(colectare4), new Point(controlPoint7), new Point(specimen4)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        move15 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(specimen4), new Point(controlPoint6), new Point(colectare2)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

         */
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
                    setPathState(2);
                }

                break;

            case 2:
                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    follower.followPath(move1, true);
                    setPathState(3);
                }
                break;

            case 3:
                if(pathTimer.getElapsedTimeSeconds() > 1.9) {
                    follower.followPath(move2, true);
                    setPathState(4);
                }

                break;

            case 4:
                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    follower.followPath(move3, true);
                    setPathState(5);
                }

                break;

            case 5:
                if(pathTimer.getElapsedTimeSeconds() > 1.9) {
                    follower.followPath(move4, true);
                    setPathState(6);
                }

                break;

            case 6:
                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    follower.followPath(move5, true);
                    setPathState(7);
                }

                break;

            case 7:
                if(pathTimer.getElapsedTimeSeconds() > 1.9) {
                    follower.followPath(move6, true);
                    brat.colectare_specimene();
                    setPathState(8);
                }

                break;

            case 8:
                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    follower.followPath(move6_2, true);
                    follower.setMaxPower(0.7);
                    brat.colectare_specimene();
                    setPathState(9);
                }

                break;

            case 9:
                if(pathTimer.getElapsedTimeSeconds() > 0.35) {
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.75) {
                    brat.specimene();
                    glisiere.specimene();
                    setPathState(10);
                }

                break;

            case 10:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move7, true);
                    follower.setMaxPower(1);
                    setPathState(11);
                }

                break;

            case 11:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move8);
                    follower.setMaxPower(0.7);
                    setPathState(12);
                }

                break;

            case 12:
                if(pathTimer.getElapsedTimeSeconds() > 0.7) {
                    glisiere.goDown(0);
                }

                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    brat.open();
                    setPathState(13);
                }

                break;

            case 13:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move9, true);
                    follower.setMaxPower(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    brat.colectare_specimene();
                    setPathState(14);
                }

                break;

            case 14:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move10, true);
                    follower.setMaxPower(0.7);
                    setPathState(15);
                }

                break;

            case 15:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    brat.specimene();
                    glisiere.specimene();
                    setPathState(16);
                }

                break;

            case 16:
                if(pathTimer.getElapsedTimeSeconds() > 0.01) {
                    follower.followPath(move11, true);
                    follower.setMaxPower(1);
                    setPathState(17);
                }

                break;

            case 17:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move12, true);
                    follower.setMaxPower(0.7);
                    setPathState(18);
                }

                break;

            case 18:
                if(pathTimer.getElapsedTimeSeconds() > 0.7) {
                    glisiere.goDown(0);
                }

                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    brat.open();
                    setPathState(19);
                }

                break;

            case 19:
                if(pathTimer.getElapsedTimeSeconds() > 0.001) {
                    follower.followPath(move13, true);
                    follower.setMaxPower(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    brat.colectare_specimene();
                    setPathState(20);
                }

                break;

            case 20:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move10, true);
                    follower.setMaxPower(0.7);
                    setPathState(21);
                }

                break;

            case 21:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    brat.specimene();
                    glisiere.specimene();
                    setPathState(22);
                }

                break;

            case 22:
                if(pathTimer.getElapsedTimeSeconds() > 0.001) {
                    follower.followPath(move14, true);
                    follower.setMaxPower(1);
                    setPathState(23);
                }

                break;

            case 23:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move15, true);
                    follower.setMaxPower(0.7);
                    setPathState(24);
                }

                break;

            case 24:
                if(pathTimer.getElapsedTimeSeconds() > 0.7) {
                    glisiere.goDown(0);
                }

                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    brat.open();
                    setPathState(25);
                }

                break;

            case 25:
                if(pathTimer.getElapsedTimeSeconds() > 0.001) {
                    follower.followPath(move16, true);
                    follower.setMaxPower(1);
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.1) {
                    brat.colectare_specimene();
                    setPathState(26);
                }

                break;

            case 26:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move10, true);
                    follower.setMaxPower(0.8);
                    setPathState(27);
                }

                break;

            case 27:
                if(pathTimer.getElapsedTimeSeconds() > 0.5) {
                    brat.close();
                }

                if(pathTimer.getElapsedTimeSeconds() > 0.9) {
                    brat.specimene();
                    glisiere.specimene();
                    setPathState(28);
                }

                break;

            case 28:
                if(pathTimer.getElapsedTimeSeconds() > 0.001) {
                    follower.followPath(move17, true);
                    follower.setMaxPower(1);
                    setPathState(29);
                }

                break;

            case 29:
                if(pathTimer.getElapsedTimeSeconds() > 1.5) {
                    follower.followPath(move18, true);
                    follower.setMaxPower(0.7);
                    setPathState(30);
                }

                break;

            case 30:
                if(pathTimer.getElapsedTimeSeconds() > 0.7) {
                    glisiere.goDown(0);
                }

                if(pathTimer.getElapsedTimeSeconds() > 1) {
                    brat.open();
                    setPathState(31);
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