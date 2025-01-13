package org.firstinspires.ftc.teamcode.Meeturi.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Meeturi.Module.BratModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.ExtendoModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.GlisiereModule;
import org.firstinspires.ftc.teamcode.Meeturi.Module.IntakeModule;
@Disabled
@Config
@Autonomous(name="AutoTime (gen mai bun ca Pedro)", group="Meeturi")
public class AutoTime extends LinearOpMode {

    DcMotorEx leftFront, leftBack, rightBack, rightFront;
    ElapsedTime runtime = new ElapsedTime();
    public static double delay1 = 0.79, delay2 = 3, delay3 = 6;

    @Override
    public void runOpMode() throws InterruptedException {
        // Inițializare module
        IntakeModule intake = new IntakeModule(hardwareMap);
        ExtendoModule extendo = new ExtendoModule(hardwareMap);
        GlisiereModule glisiere = new GlisiereModule(hardwareMap);
        BratModule brat = new BratModule(hardwareMap);

        // Inițializare motoare
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        // Initializează modulele hardware
        intake.init_auto();
        extendo.init();
        glisiere.init();
        brat.init_auto();

        telemetry.addData("Status", "Ready to start");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // Secvență de comenzi

            // Pas 1: Mergi înainte pentru 0.5 secunde
            mergi();
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < delay1) {
                glisiere.update(); // Actualizează PID în buclă
            }
            stai();

            // Pas 2: Punctare automat

            // Pas 3: Colectare
            brat.colectare();
            glisiere.goDown();
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < delay3) {
                glisiere.update(); // Actualizează PID
            }

            // Oprire completă
            stai();
        }

        telemetry.addData("Status", "Complete");
        telemetry.update();
        sleep(1000); // Mică pauză pentru a afișa starea finală
    }

    public void mergi() {
        leftFront.setPower(0.3);
        leftBack.setPower(0.3);
        rightBack.setPower(0.3);
        rightFront.setPower(0.3);
    }

    public void stai() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }
}
