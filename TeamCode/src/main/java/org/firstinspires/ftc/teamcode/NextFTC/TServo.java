package org.firstinspires.ftc.teamcode.NextFTC;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class TServo extends Subsystem {
    public static final TServo INSTANCE = new TServo();
    Servo servo;
    public Command open_servo() {
        return new ServoToPosition(servo, 0.5, this);
    }

    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "servo");
    }
}
