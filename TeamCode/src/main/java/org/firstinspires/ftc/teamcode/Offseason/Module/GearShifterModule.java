package org.firstinspires.ftc.teamcode.Offseason.Module;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class GearShifterModule {
    HardwareMap hardwareMap;
    public GearShifterModule(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    Servo gear_right, gear_left;

    public void init() {
        gear_right = hardwareMap.get(Servo.class, "gear_right");
        gear_left = hardwareMap.get(Servo.class, "gear_left");

        gear_right.setPosition(0.33);
        gear_left.setPosition(0.33);
    }

    public void torque() {
        gear_right.setPosition(0.17);
        gear_left.setPosition(0.17);
    }

    public void speed() {
        gear_right.setPosition(0.33);
        gear_left.setPosition(0.33);
    }

}
