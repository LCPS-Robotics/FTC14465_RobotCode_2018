package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class PracticeBotCode extends OpMode {

    private ElapsedTime runTime = new ElapsedTime();

    private boolean driveTank = true;
    double toggleTime;

    //leftDrive Motors
    private DcMotor lFDrive = null;
    private DcMotor lRDrive = null;

    //rightDrive Motors
    private DcMotor rFDrive = null;
    private DcMotor rRDrive = null;


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        lFDrive = hardwareMap.get(DcMotor.class, "LFDrive");
        lRDrive = hardwareMap.get(DcMotor.class, "LRDrive");
        rFDrive = hardwareMap.get(DcMotor.class, "RFDrive");
        rRDrive = hardwareMap.get(DcMotor.class, "RRDrive");

        rFDrive.setDirection(DcMotor.Direction.REVERSE);
        rRDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void start() {
        runTime.reset();
        toggleTime = runTime.seconds();
    }

    @Override
    public void loop() {
        if (driveTank) {
            tankDrive();
        } else {
            arcadeDrive();
        }

        if (gamepad1.start) {
            toggle();
        }
    }

    public void toggle() {
        if (toggleTime < runTime.seconds() - 1) {
            if (driveTank) {
                driveTank = false;
            } else {
                driveTank = true;
            }
        }

        toggleTime = runTime.seconds();
    }

    public void tankDrive() {
        double leftPower;
        double rightPower;

        leftPower = gamepad1.left_stick_y;
        rightPower = gamepad1.right_stick_y;

        //deadZone
        if (leftPower < .1 && leftPower > -.1) {
            leftPower = 0;
        }

        if (rightPower < .1 && rightPower > -.1) {
            rightPower = 0;
        }

        lFDrive.setPower(leftPower);
        lRDrive.setPower(leftPower);

        rFDrive.setPower(rightPower);
        rRDrive.setPower(rightPower);
    }

    public void arcadeDrive() {
        double powY;
        double powX;
        double leftPower;
        double rightPower;

        powY = gamepad1.left_stick_y;
        powX = gamepad1.left_stick_x;

        if (powX < -.05) {
            leftPower = powX;
            lRDrive.setPower(leftPower);
            lFDrive.setPower(leftPower);

            rightPower = -powX;
            rFDrive.setPower(rightPower);
            rRDrive.setPower(rightPower);
        } else if (powX > .05) {
            leftPower = powX;
            lRDrive.setPower(leftPower);
            lFDrive.setPower(leftPower);

            rightPower = -powX;
            rFDrive.setPower(rightPower);
            rRDrive.setPower(rightPower);
        } else {
            leftPower = powY;
            rightPower = powY;

            lFDrive.setPower(leftPower);
            lRDrive.setPower(leftPower);
            rFDrive.setPower(rightPower);
            rRDrive.setPower(rightPower);
        }
    }
}