package org.firstinspires.ftc.teamcode.Offseason.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Offseason.Module.BratModule;
import org.firstinspires.ftc.teamcode.Offseason.Module.IntakeModule;
@TeleOp(name = "Clapita zdr gen")
public class Test_marimeclapita extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        BratModule brat = new BratModule(hardwareMap);
        IntakeModule intake = new IntakeModule(hardwareMap);

        brat.init();
        intake.init_test();

        waitForStart();

        while (opModeIsActive()) {

        }

    }
}
