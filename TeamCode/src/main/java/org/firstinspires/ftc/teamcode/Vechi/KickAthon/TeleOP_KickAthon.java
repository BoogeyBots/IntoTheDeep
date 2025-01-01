package org.firstinspires.ftc.teamcode.Vechi.KickAthon;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
@Disabled
@TeleOp
public class TeleOP_KickAthon extends LinearOpMode {
    public Servo servo_brat, servo_gheara;

    public double kp = 0.0077;
    public double ki = 0.1777;
    public double kd = 0.001;
    public double kf = 0.27;
    public PIDController controller = new PIDController(kp, ki, kd);
    int target = 0;
    public DcMotorEx motor_brat, motor_ata;
    private final double ticks_in_degree = 1.493611111;


    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        servo_brat = hardwareMap.get(Servo.class, "servo_brat");
        servo_gheara = hardwareMap.get(Servo.class, "servo_gheara");
        motor_brat = hardwareMap.get(DcMotorEx.class, "motor_brat");
        ElapsedTime timp = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        servo_brat_preluare();

        motor_brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_brat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        servo_brat.setDirection(Servo.Direction.REVERSE);
        servo_gheara.setDirection(Servo.Direction.REVERSE);

        servo_brat.setPosition(0.1217);
        servo_gheara.setPosition(1);

        motor_ata = hardwareMap.get(DcMotorEx.class, "motor_ata");
        motor_ata.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            timp.startTime();
            drive.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.left_stick_y,
                            - gamepad1.left_stick_x * 0.5,
                            gamepad1.right_stick_x
                    )
            );

            drive.update();


            update();

            if(gamepad1.left_bumper) {
                servo_brat_preluare();
                target = 100;
                timp.reset();
            }

            if(timp.seconds() > 1.2 && target == 100) {
                timp.reset();
                target = 0;
            }

            if(gamepad1.right_bumper) {
                target = 420;
                servo_brat_punere();
            }

            if(gamepad1.a) {
                servo_gheara_open();
            }

            if(gamepad1.b) {
                servo_gheara_close();
            }

            if (gamepad1.left_trigger > 0.1) {
                motor_ata.setPower(gamepad1.left_trigger);
            }
        }
    }

    public void servo_brat_preluare() {
        servo_brat.setPosition(0.1217);
    }

    public void servo_brat_punere() {
        servo_brat.setPosition(0.177);
    }

    public void servo_gheara_close() {
        servo_gheara.setPosition(0.6);
    }

    public void servo_gheara_open() {
        servo_gheara.setPosition(1);
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
