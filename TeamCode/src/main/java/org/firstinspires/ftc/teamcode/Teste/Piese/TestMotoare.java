package org.firstinspires.ftc.teamcode.Teste.Piese;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class TestMotoare extends LinearOpMode {
    Servo servoDR, servoST;
    DcMotorEx motorDR, motorST_ENC;
    @Override
    public void runOpMode() throws InterruptedException {
        motorST_ENC = hardwareMap.get(DcMotorEx.class, "motorST");
        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");
        //servoDR = hardwareMap.get(Servo.class, "servoDR");
        //servoST = hardwareMap.get(Servo.class, "servoST");

        motorDR.setDirection(DcMotorSimple.Direction.REVERSE);

        motorST_ENC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorDR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorST_ENC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorDR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //servoDR.setPosition(0.6);
        //servoST.setPosition(0.6);

        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.right_bumper) {
                motorDR.setPower(1);
                motorST_ENC.setPower(1);
            }

            else if(gamepad1.left_bumper) {
                motorDR.setPower(-1);
                motorST_ENC.setPower(-1);
            }

            else {
                motorST_ENC.setPower(0);
                motorDR.setPower(0);
            }
        }

    }
}
