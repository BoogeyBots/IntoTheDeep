package org.firstinspires.ftc.teamcode.Teste.Piese;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
@TeleOp
public class Test_touchsensor extends LinearOpMode {
    TouchSensor touchSensor;
    @Override
    public void runOpMode() throws InterruptedException {
        touchSensor = hardwareMap.get(TouchSensor.class, "sensor");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Senzor:", touchSensor.isPressed());
            telemetry.update();
        }

    }
}
