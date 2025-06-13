package org.firstinspires.ftc.teamcode.Offseason.Teste.Sisteme;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Offseason.Module.BratModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.IntakeModule;
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

            if(gamepad1.y) {
                brat.gheara();
            }

            if(gamepad1.dpad_right) {
                brat.open();
            }

            if(gamepad1.dpad_left) {
                brat.close();
            }

            if(gamepad1.dpad_up) {
                glisiere.goDown(poz);
            }

            if(gamepad1.dpad_down) {
                glisiere.goDown(0);
            }

            glisiere.update();
        }
    }
}
