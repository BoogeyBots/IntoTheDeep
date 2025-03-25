package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.util.CustomFilteredPIDFCoefficients;
import com.pedropathing.util.CustomPIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.PINPOINT;

        FollowerConstants.leftFrontMotorName = "leftFront";
        FollowerConstants.leftRearMotorName = "leftRear";
        FollowerConstants.rightFrontMotorName = "rightFront";
        FollowerConstants.rightRearMotorName = "rightRear";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.REVERSE;

        FollowerConstants.mass = 15.55;

        FollowerConstants.xMovement = 85.52845674352331;
        FollowerConstants.yMovement = 67.116320541;

        FollowerConstants.forwardZeroPowerAcceleration = -170;
        FollowerConstants.lateralZeroPowerAcceleration = -200;

        //FollowerConstants.translationalPIDFCoefficients = new CustomPIDFCoefficients(0.3,0,0.03,0);
        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.3, 0, 0.03, 0);
        FollowerConstants.useSecondaryTranslationalPID = false;
        //FollowerConstants.secondaryTranslationalPIDFCoefficients = new CustomPIDFCoefficients(0.1,0,0.01,0); // Not being used, @see useSecondaryTranslationalPID
        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0, 0, 0, 0);

        //FollowerConstants.headingPIDFCoefficients = new CustomPIDFCoefficients(2,0,0.06,0);
        FollowerConstants.headingPIDFCoefficients.setCoefficients(2, 0, 0.06, 0);
        FollowerConstants.useSecondaryHeadingPID = false;
        //FollowerConstants.secondaryHeadingPIDFCoefficients = new CustomPIDFCoefficients(2,0,0.1,0); // Not being used, @see useSecondaryHeadingPID
        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(0,0, 0.2, 0);

        //FollowerConstants.drivePIDFCoefficients = new CustomFilteredPIDFCoefficients(0.02,0,0.000001,0.4,0);
        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.005, 0, 0.0009, 0.4, 0);
        FollowerConstants.useSecondaryDrivePID = true;
        //FollowerConstants.secondaryDrivePIDFCoefficients = new CustomFilteredPIDFCoefficients(0.01,0,0.0000005,0.4,0); // Not being used, @see useSecondaryDrivePID
        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0.005 / 2, 0, 0.0009 / 2, 0.4, 0);

        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
        FollowerConstants.centripetalScaling = 0.0005;

        FollowerConstants.pathEndTimeoutConstraint = 50;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 150;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
