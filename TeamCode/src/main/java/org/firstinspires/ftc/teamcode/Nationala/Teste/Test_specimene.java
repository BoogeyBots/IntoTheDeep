package org.firstinspires.ftc.teamcode.Nationala.Teste;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Nationala.Module.BratModule;
import org.firstinspires.ftc.teamcode.Nationala.Module.GlisiereModule;
@Config
@TeleOp
public class Test_specimene extends LinearOpMode {
    public static double timer;
    @Override
    public void runOpMode() throws InterruptedException {
        GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        BratModule brat = new BratModule(hardwareMap);

        glisiere.init();
        brat.init();

        ElapsedTime gheara = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        boolean inchis = false;

        waitForStart();

        while (opModeIsActive()) {
            glisiere.update();
            gheara.startTime();

            if(gamepad1.right_bumper) {
                glisiere.goDown(700);
                brat.specimene();
            }

            if(gamepad1.left_bumper) {
                glisiere.goDown(0);
                inchis = true;
                gheara.reset();
            }

            if(inchis && gheara.seconds() > timer) {
                brat.open();
                inchis = false;
            }

            if(gamepad1.dpad_up) {
                brat.close();
            }

            if(gamepad1.dpad_down) {
                brat.open();
            }

            telemetry.addData("Inchis: ", inchis);
            telemetry.addData("Timer: ", gheara.seconds());
            telemetry.update();
        }

    }
}
