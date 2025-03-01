package org.firstinspires.ftc.teamcode.Nationala.Module;

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

        gear_right.setPosition(0.27);
        gear_left.setPosition(0.27);
    }

    public void torque() {
        gear_right.setPosition(0.05);
        gear_left.setPosition(0.05);
    }

    public void speed() {
        gear_right.setPosition(0.27);
        gear_left.setPosition(0.27);
    }

}
