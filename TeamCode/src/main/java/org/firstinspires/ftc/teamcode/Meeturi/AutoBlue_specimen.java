package org.firstinspires.ftc.teamcode.Meeturi;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.util.Timer;

public class AutoBlue_specimen extends LinearOpMode {
    Follower follower;
    Timer pathTimer, actionTimer, opmodeTimer;
    //am de înlocuit valorile
    private final Pose startPose = new Pose(0, 0, 0);
    private final Pose scorePosePreload = new Pose(0, 0, 0);
    private final Pose moveBlueSamplePose = new Pose(0, 0, 0);
    private final Pose toHumanPlayerPose = new Pose(0, 0, 0);
    private final Pose endPose = new Pose(0, 0, 0);

    @Override
    public void runOpMode() throws InterruptedException {

    }
}
