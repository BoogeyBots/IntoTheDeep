package org.firstinspires.ftc.teamcode.Nationala.Teste.Sisteme;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Nationala.Module.BratModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.IntakeModule;

@TeleOp
public class Test_brat extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        IntakeModule intake = new IntakeModule(hardwareMap);
        BratModule brat = new BratModule(hardwareMap);

        brat.init();
        intake.init();

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                brat.brat();
            }

            if(gamepad1.dpad_up) {
                intake.open();
            }

            if(gamepad1.dpad_down) {
                intake.close();
            }

            if(gamepad1.dpad_right) {
                brat.open();
            }

            if(gamepad1.dpad_left) {
                brat.close();
            }

            if(gamepad1.circle) {
                brat.miscare_gheara();
            }

            if(gamepad1.triangle) {
                brat.rotire_gheara();
            }
        }
    }
}
