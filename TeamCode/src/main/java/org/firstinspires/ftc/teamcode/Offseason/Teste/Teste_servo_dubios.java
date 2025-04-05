package org.firstinspires.ftc.teamcode.Offseason.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp
public class Teste_servo_dubios extends LinearOpMode {
    Servo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "rotire_gheara");

        ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        ElapsedTime timer2 = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        boolean bool = false;
        boolean bool2 = false;

        waitForStart();

        while (opModeIsActive()) {
            timer.startTime();
            if(gamepad1.a) {
                servo.setPosition(0.5);
                timer.reset();
                bool = true;
            }

            if(bool && timer.seconds() > 0.1) {
                servo.setPosition(0.155);
                bool = false;
            }

            if(gamepad1.b) {
                servo.setPosition(0.5);
                timer2.reset();
                bool2 = true;
            }

            if(bool2 && timer2.seconds() > 0.1) {
                servo.setPosition(0.815);
                bool2 = false;
            }

        }

    }
}
