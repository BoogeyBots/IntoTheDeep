package org.firstinspires.ftc.teamcode.Teste.Module;


import static java.lang.Math.abs;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp (name = "Tunare PID glisiere")
public class PID_glisiere extends LinearOpMode{
    public DcMotorEx motorST_ENC, motorDR;
    public Servo servoDR, servoST;
    int poz_min = 0;
    int poz_max = 2000;
    public static double p = 7, i = 2.7, d = 0.47;
    public static int target = 0;
    int modifier = 10;
    PIDController controller = new PIDController(p, i, d);
    FtcDashboard dashboard;
    @Override
    public void runOpMode() throws InterruptedException {

        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motorST_ENC = hardwareMap.get(DcMotorEx.class, "motorST_ENC");
        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        motorDR.setDirection(DcMotorSimple.Direction.REVERSE);

        motorST_ENC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorDR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorST_ENC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorDR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        controller.reset();

        //servoDR.setPosition(0.6);
        //servoST.setPosition(0.6);

        waitForStart();

        while (opModeIsActive()) {
            update();

            if (gamepad1.a) {
                controller.setSetPoint(600);
            }

            if(gamepad1.b) {
                controller.setSetPoint(1600);
            }

            else {
                controller.setSetPoint(controller.getSetPoint());
            }



            dashboard.updateConfig();
            telemetry.addData("A AJUNS?", controller.atSetPoint());
            telemetry.addData("Pozitia ST: ", motorST_ENC.getCurrentPosition());
            telemetry.addData("Pozitie DR: ", motorDR.getCurrentPosition());
            telemetry.addData("Controller pozition: ", controller.getSetPoint());
            telemetry.addData("Pozitia care trb atinsa", controller.getSetPoint());
            //telemetry.addData("Eroare Pozitie", controller.getPositionError());
            telemetry.update();
        }
    }

    public void update() {
        controller.setPID(p, i, d);
        if (!controller.atSetPoint() || controller.getSetPoint() != motorST_ENC.getCurrentPosition()) {
            double output = controller.calculate(
                    motorST_ENC.getCurrentPosition()
            );

            motorST_ENC.setVelocity(output);
            motorDR.setVelocity(output);
        }
    }
}