package org.firstinspires.ftc.teamcode.Offseason.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@TeleOp
public class Test_DistanceSensor extends LinearOpMode {
    DistanceSensor sensor;
    @Override
    public void runOpMode() throws InterruptedException {
        sensor = hardwareMap.get(DistanceSensor.class, "sensor");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Distance:", sensor.getDistance(DistanceUnit.CM));
            telemetry.update();
        }

    }
}
