package org.firstinspires.ftc.teamcode.Offseason.Teste.Sisteme;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Offseason.Module.GearShifterModule;

@Config
@TeleOp (name = "Tunare PID glisiere")
public class PID_glisiere extends LinearOpMode {
    public DcMotorEx motorST_ENC, motorDR;
    public Servo servoDR, servoST;
    int poz_min = 0;
    int poz_max = 2000;
    public static double poz_servo;
    Servo rotire_left, rotire_right, clapita;
    DcMotorEx motor;

    Servo servo_gheara, box_tube;


    public static double p = 0, i = 0, d = 0;

    public static int target = 0;
    int modifier = 10;
    PIDController controller = new PIDController(p, i, d);
    FtcDashboard dashboard;
    @Override
    public void runOpMode() throws InterruptedException {
        GearShifterModule gear = new GearShifterModule(hardwareMap);

        gear.init();

        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motorST_ENC = hardwareMap.get(DcMotorEx.class, "motorST");
        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");
        rotire_right = hardwareMap.get(Servo.class, "rotire_right");
        rotire_left = hardwareMap.get(Servo.class, "rotire_left");
        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");

        motorDR.setDirection(DcMotorSimple.Direction.REVERSE);

        motorST_ENC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorDR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorST_ENC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorDR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        controller.reset();

        //servoDR.setPosition(0.2);
      //  servoST.setPosition(0.2);
        rotire_right.setPosition(0.8);
        rotire_left.setPosition(0.8);
        servo_gheara.setPosition(0.8);
        waitForStart();

        while (opModeIsActive()) {
            update();

            if(gamepad1.a) {
                servoDR.setPosition(poz_servo);
                servoST.setPosition(poz_servo);
            }

            if(gamepad1.b) {
                controller.setSetPoint(target);
            }

            if(gamepad1.y) {
                controller.setSetPoint(0);
            }

            else {
                controller.setSetPoint(controller.getSetPoint());
            }

            if(gamepad1.dpad_up) {
                gear.speed();
            }

            if(gamepad1.dpad_down) {
                gear.torque();
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
        if (!controller.atSetPoint() || controller.getSetPoint() != motorDR.getCurrentPosition()) {
            double output = controller.calculate(
                    motorDR.getCurrentPosition()
            );

            motorST_ENC.setVelocity(output);
            motorDR.setVelocity(output);
        }
    }
}