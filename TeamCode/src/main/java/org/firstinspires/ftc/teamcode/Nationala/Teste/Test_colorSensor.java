package org.firstinspires.ftc.teamcode.Nationala.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp
public class Test_colorSensor extends LinearOpMode {
    ColorSensor sensor;
    @Override
    public void runOpMode() throws InterruptedException {
        sensor = hardwareMap.get(ColorSensor.class, "colorsensor");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Rosu: ", sensor.red());
            telemetry.addData("Albastru: ", sensor.blue());
            telemetry.addData("Verde: ", sensor.green());
            telemetry.addData("ARGB: ", sensor.argb());
            telemetry.update();

        }
    }
}
