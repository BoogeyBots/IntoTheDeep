package org.firstinspires.ftc.teamcode.Nationala.Teste.Sisteme;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Nationala.Module.IntakeModule;

@Config
@TeleOp
public class Test_intake extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        IntakeModule intake = new IntakeModule(hardwareMap);

        intake.init();

        waitForStart();

        while (opModeIsActive()) {

            if(gamepad1.right_trigger > 0.001) {
                intake.trage(gamepad1.right_trigger);
            }

            else if(gamepad1.left_trigger > 0.001) {
                intake.scuipa(gamepad1.left_trigger);
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

            if (gamepad1.y) {
                intake.gasire();
            }

            if(gamepad1.dpad_up) {
                intake.open();
            }

            if(gamepad1.dpad_down) {
                intake.close();
            }

            telemetry.addData("Power:", gamepad1.right_trigger * 0.7);
            telemetry.update();


        }
    }
}
