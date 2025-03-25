package org.firstinspires.ftc.teamcode.Nationala.Teste.Sisteme;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Nationala.Module.BratModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.IntakeModule;
@Config
@TeleOp
public class Test_brat extends LinearOpMode {
    public static int poz;
    @Override
    public void runOpMode() throws InterruptedException {
        BratModule brat = new BratModule(hardwareMap);
        GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);

        brat.init_specimene();
        glisiere.init();
        intake.init();

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                brat.brat();
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

            if(gamepad1.dpad_up) {
                glisiere.goDown(poz);
            }

            if(gamepad1.dpad_down) {
                glisiere.goDown(-10);
            }

            glisiere.update();
        }
    }
}
