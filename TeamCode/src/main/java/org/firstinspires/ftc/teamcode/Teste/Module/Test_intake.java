package org.firstinspires.ftc.teamcode.Teste.Module;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Demo.Module.IntakeModule;

@TeleOp
public class Test_intake extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        IntakeModule intake = new IntakeModule(hardwareMap);

        intake.init();

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.right_trigger > 0.001) {
                intake.trage(gamepad1.right_trigger * 0.7);
            }

            else if(gamepad1.left_trigger > 0.001) {
                intake.scuipa(gamepad1.left_trigger * 0.7);
            }

            else {
                intake.stop();
            }

            if(gamepad1.a) {
                intake.sus();
            }

            if(gamepad1.b) {
                intake.jos();
            }
        }
    }
}
