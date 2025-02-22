package org.firstinspires.ftc.teamcode.Teste.Piese;

import android.graphics.Color;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Config
@TeleOp
public class Test_distanceSensor extends LinearOpMode {
    DistanceSensor sensor;
    ColorSensor sensor2;
    public static double red, green, blue;
    @Override
    public void runOpMode() throws InterruptedException {
        sensor = hardwareMap.get(DistanceSensor.class, "sensor");
        sensor2 = hardwareMap.get(ColorSensor.class, "sensor2");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Distanta: ", sensor.getDistance(DistanceUnit.CM));

            /*if(sensor.getDistance(DistanceUnit.CM) < 1.7) {
                if(sensor2.red() >= 1000 && sensor2.green() >= 1200) {
                    gamepad1.setLedColor(1, 0.7, 0, 500);
                }

                if(sensor2.blue() >= 800 && sensor2.green() <= 500) {
                    gamepad1.setLedColor(0, 0, 1, 500);
                }

                if(sensor2.red() >= 800 && sensor2.green() <= 700) {
                    gamepad1.setLedColor(1, 0, 0, 500);
                }
            }

             */

            gamepad1.setLedColor(red, green, blue, 500);

            telemetry.addData("Light detected: ", ((OpticalDistanceSensor) sensor2).getLightDetected());
            telemetry.addData("Red: ", sensor2.red());
            telemetry.addData("Green: ", sensor2.green());
            telemetry.addData("Blue: ", sensor2.blue());

            telemetry.update();
        }

    }
}
