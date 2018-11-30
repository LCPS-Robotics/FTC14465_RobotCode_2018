package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Mecanum Robot", group="Teleop")
public class RobotMecanum extends OpMode {

    private DcMotor lFDrive = null;
    private DcMotor lRDrive = null;
    private DcMotor rFDrive = null;
    private DcMotor rRDrive = null;

    @Override
    public void init() {
        lFDrive = hardwareMap.get(DcMotor.class, "LFDrive");
        lRDrive = hardwareMap.get(DcMotor.class, "LRDrive");
        rFDrive = hardwareMap.get(DcMotor.class, "RFDrive");
        rRDrive = hardwareMap.get(DcMotor.class, "RRDrive");

        rFDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rRDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        mecanumDrive(gamepad1.left_stick_x,-gamepad1.left_stick_y, -gamepad1.right_stick_x);

    }

    public void mecanumDrive(double x, double y, double rotate){
        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0] = x + y + rotate;
        wheelSpeeds[1] = -x + y - rotate;
        wheelSpeeds[2] = -x + y + rotate;
        wheelSpeeds[3] = x + y - rotate;

        normalize(wheelSpeeds);

        lFDrive.setPower(wheelSpeeds[0]);
        rFDrive.setPower(wheelSpeeds[1]);
        lRDrive.setPower(wheelSpeeds[2]);
        rRDrive.setPower(wheelSpeeds[3]);
    }

    private void normalize(double[] wheelSpeeds){
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++){
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude){
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0){
            for (int i = 0; i < wheelSpeeds.length; i++){
                wheelSpeeds[i] /= maxMagnitude;
            }
        }

    }
}
