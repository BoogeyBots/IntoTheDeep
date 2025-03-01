package org.firstinspires.ftc.teamcode.Nationala.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Nationala.Module.GearShifterModule;
@TeleOp
public class Glisiere_noPID extends LinearOpMode {
    DcMotorEx motorDR, motorST;
    @Override
    public void runOpMode() throws InterruptedException {
        GearShifterModule gear = new GearShifterModule(hardwareMap);
        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");
        motorST = hardwareMap.get(DcMotorEx.class, "motorST");
        motorDR.setDirection(DcMotorSimple.Direction.REVERSE);

        gear.init();

        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.a) {
                motorDR.setDirection(DcMotorSimple.Direction.REVERSE);
                motorST.setDirection(DcMotorSimple.Direction.FORWARD);
            }

            if(gamepad1.b) {
                motorDR.setDirection(DcMotorSimple.Direction.FORWARD);
                motorST.setDirection(DcMotorSimple.Direction.REVERSE);
            }

            if(gamepad1.triangle) {
                gear.torque();
            }

            if(gamepad1.square) {
                gear.speed();
            }

            if(gamepad1.right_trigger > 0.01) {
                motorDR.setPower(gamepad1.right_trigger);
                motorST.setPower(gamepad1.right_trigger);
            }

            else if(gamepad1.left_trigger > 0.01) {
                motorDR.setPower(-gamepad1.left_trigger);
                motorST.setPower(-gamepad1.left_trigger);
            }

            else {
                motorDR.setPower(0);
                motorST.setPower(0);
            }

            telemetry.addData("Dreapta: ", motorDR.getDirection());
            telemetry.addData("Power: ", motorDR.getPower());
            telemetry.update();
        }

    }
}
