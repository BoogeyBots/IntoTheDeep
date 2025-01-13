package org.firstinspires.ftc.teamcode.Vechi.KickAthon;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Disabled
@Config
@TeleOp (name = "PIDF config al nostru")
public class PIDF_brat_config extends LinearOpMode {

    Servo servo_brat;

    static double kp = 0, ki = 0, kd = 0, kf = 0;
    PIDController controller = new PIDController(kp, ki, kd);
    static int target = 0;
    DcMotorEx motor_brat;

    private final double ticks_in_degree = 1.493611111;


    FtcDashboard dashboard;

    @Override
    public void runOpMode() throws InterruptedException {
        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motor_brat = hardwareMap.get(DcMotorEx.class, "motor_brat");
        servo_brat = hardwareMap.get(Servo.class, "servo_brat");
        servo_brat.setDirection(Servo.Direction.REVERSE);

        motor_brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_brat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        controller.reset();

        waitForStart();

        while (opModeIsActive()) {

            servo_brat.setPosition(0.0394);

            if(gamepad1.a) {
                controller.setSetPoint(target);
            }

            dashboard.updateConfig();
            update();

            telemetry.addData("A AJUNS?", controller.atSetPoint());
            telemetry.addData("Target: ", target);
            telemetry.addData("Poziție motor: ", motor_brat.getCurrentPosition());
            telemetry.addData("PID poziție: ", controller.getSetPoint());
            telemetry.update();
        }
    }

    public void update() {
        controller.setPID(kp, ki, kd);
        int poz = motor_brat.getCurrentPosition();
        double pid = controller.calculate(poz, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * kf;

        double power = pid + ff;
        motor_brat.setPower(power);


    }
}
