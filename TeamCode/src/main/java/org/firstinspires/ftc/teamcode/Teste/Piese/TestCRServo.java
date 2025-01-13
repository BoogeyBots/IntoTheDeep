package org.firstinspires.ftc.teamcode.Teste.Piese;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
@Disabled
@TeleOp
public class TestCRServo extends LinearOpMode {
    private CRServo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(CRServo.class, "servo");
        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                servo.setPower(1);
            }
            else if(gamepad1.b) {
                servo.setPower(-1);
            }
            else {
                servo.setPower(0);
            }
        }

    }
}
