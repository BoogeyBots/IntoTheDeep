package org.firstinspires.ftc.teamcode.NextFTC;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;

import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

public class TeleOP_next extends NextFTCOpMode {
    public TeleOP_next() {
        super(TServo.INSTANCE);
    }

    public Command driverControlled;

    @Override
    public void onInit() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    }

    @Override
    public void onStartButtonPressed() {
        driverControlled.invoke();
    }
}
