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
@TeleOp (name = "Extendo")
public class PID_extendo extends LinearOpMode {
    public DcMotorEx motor;
    int poz_min = 0;
    int poz_max = 2000;
    public static double kp = 0, ki = 0, kd = 0;
    public static int target = 0;
    private Servo rotire_left, rotire_right;
    int modifier = 10;
    PIDController controller = new PIDController(kp, ki, kd);
    FtcDashboard dashboard;
    @Override
    public void runOpMode() throws InterruptedException {

        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motor = hardwareMap.get(DcMotorEx.class, "motor_extendo");

        rotire_right = hardwareMap.get(Servo.class, "rotire_right");
        rotire_left = hardwareMap.get(Servo.class, "rotire_left");

        rotire_right.setPosition(0.12);
        rotire_left.setPosition(0.12);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        controller.reset();

        waitForStart();

        while (opModeIsActive()) {
            update();
            if (gamepad1.a) {
                controller.setSetPoint(600);
            }

            if(gamepad1.b) {
                controller.setSetPoint(2000);
            }

            else {
                controller.setSetPoint(controller.getSetPoint());
            }



            dashboard.updateConfig();
            telemetry.addData("A AJUNS?", controller.atSetPoint());
            telemetry.addData("Pozitia ST: ", motor.getCurrentPosition());
            //telemetry.addData("Pozitie DR: ", motorDR.getCurrentPosition());
            telemetry.addData("Controller pozition: ", controller.getSetPoint());
            telemetry.addData("Pozitia care trb atinsa", controller.getSetPoint());
            //telemetry.addData("Eroare Pozitie", controller.getPositionError());
            telemetry.update();
        }
    }

    public void update() {
        controller.setPID(kp, ki, kd);
        if (!controller.atSetPoint() || controller.getSetPoint() != motor.getCurrentPosition()) {
            double output = controller.calculate(
                    motor.getCurrentPosition()
            );
            motor.setVelocity(output);
        }
    }
}