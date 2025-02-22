package org.firstinspires.ftc.teamcode.Teste.Module;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Meeturi.Module.BratModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.IntakeModule;
@Config
@TeleOp
public class Test_brat extends LinearOpMode {
    public static int target = 0;
    public static int poz = 0;
    Servo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        BratModule brat = new BratModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);
        //GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        Servo servo = hardwareMap.get(Servo.class, "rotire_gheara");

        ElapsedTime timp = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        brat.init();
        intake.init();
        //glisiere.init();
        servo.setPosition(0);

        boolean inchis = false;

        waitForStart();

        while (opModeIsActive()) {
            /*timp.startTime();
            if (gamepad1.right_bumper) {
                timp.reset();
                inchis = true;
                servo_gheara.setPosition(0.517);
            }

            if(inchis && timp.seconds() > 3) {
                rotire_gheara.setPosition(0.1211);
                servoDR.setPosition(0.5789);
                servoST.setPosition(0.5789);
                inchis = false;
            }

            if (gamepad1.left_bumper) {
                servo_gheara.setPosition(0.39);
                rotire_gheara.setPosition(0.6444);
                servoDR.setPosition(0.1567);
                servoST.setPosition(0.1567);
            }

             */

            if(gamepad1.a) {
                brat.brat();
            }

            if(gamepad1.b) {
                brat.rotire();
            }

            if(gamepad1.dpad_down) {
                brat.close();
            }

            if(gamepad1.dpad_up) {
                brat.open();
            }

            if (gamepad1.dpad_left) {
                intake.close();
            }

            if(gamepad1.dpad_right) {
                intake.open();
            }

            if (gamepad1.y) {
                brat.gheara();
            }
            if (gamepad1.x) {
                servo.setPosition(poz);
            }


            telemetry.update();

        }

    }
}
