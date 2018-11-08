package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.sql.Driver;

@Autonomous(name="Silver Auto", group="14465")
public class silverAuton extends LinearOpMode {
    //lFDrive Motors
    private DcMotor lFDrive = null;
    private DcMotor lRDrive = null;

    //rFDrive Motors
    private DcMotor rFDrive = null;
    private DcMotor rRDrive = null;

    //Arm Motors
    private DcMotor upArm = null;
    private DcMotor lowArm = null;

    //Bucket Motors
    private DcMotor bucketAngle = null;
    private DcMotor bucketFlaps = null;

    //Climbing Servos
    private Servo lift1 = null;
    private Servo lift2 = null;

    private ElapsedTime runTime = new ElapsedTime();

    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_MOTOR_REV = 1440;
    static final double DRIVE_GEAR_REDUCTION = 1.0;

    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    @Override
    public void runOpMode() throws InterruptedException {
        lFDrive = hardwareMap.get(DcMotor.class, "LFDrive");
        lRDrive = hardwareMap.get(DcMotor.class, "LRDrive");

        rFDrive = hardwareMap.get(DcMotor.class, "RFDrive");
        rRDrive = hardwareMap.get(DcMotor.class, "RRDrive");

        upArm = hardwareMap.get(DcMotor.class, "UpArm");
        lowArm = hardwareMap.get(DcMotor.class, "LowArm");

        bucketAngle = hardwareMap.get(DcMotor.class, "bucketAngle");
        bucketFlaps = hardwareMap.get(DcMotor.class, "bucketFlap");

        lift1 = hardwareMap.get(Servo.class, "lift1");
        lift2 = hardwareMap.get(Servo.class, "lift2");

        rFDrive.setDirection(DcMotor.Direction.REVERSE);
        rRDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        lFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lRDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rRDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Path0", "Starting at %7d : %7d", lFDrive.getCurrentPosition(), rFDrive.getCurrentPosition());
        telemetry.update();

        waitForStart();

        climbDown();
        encoderDrive(DRIVE_SPEED, 6, 6, 3.0);
        encoderDrive(TURN_SPEED, -12, 12, 3.0);
        encoderDrive(DRIVE_SPEED, 12, 12, 4.0);
        encoderDrive(TURN_SPEED, -12, 12, 3.0);
        encoderDrive(DRIVE_SPEED, 18, 18, 5.0);
        encoderDrive(TURN_SPEED, -4, 4, 2.0);
        encoderDrive(DRIVE_SPEED, 24, 24, 6.0);
        encoderDrive(TURN_SPEED, 22.667, -22.667, 5.0);
        encoderDrive(DRIVE_SPEED, 41, 41, 10.0);

    }

    public void bucketControl(double speed, int position, boolean scooperOn, boolean in) {
        if (scooperOn) {
            //scooper on
            if (in) {
                //scoop in
                bucketFlaps.setPower(.5);
            } else {
                //scoop out
                bucketFlaps.setPower(-.5);
            }
            bucketAngle.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bucketAngle.setTargetPosition(position);
            bucketAngle.setPower(speed);
            while (bucketAngle.isBusy()) {

            }
            bucketAngle.setPower(0);
        } else {
            bucketFlaps.setPower(0);
            bucketAngle.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bucketAngle.setTargetPosition(position);
            bucketAngle.setPower(speed);
            while (bucketAngle.isBusy()) {

            }
            bucketAngle.setPower(0);
        }


    }

    public void armControl(double speed, int position, boolean Up) {
        upArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lowArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (Up) {
            //if up arm
            upArm.setTargetPosition(position);
            upArm.setPower(speed);
        } else {
            //if low arm
            lowArm.setTargetPosition(position);
            lowArm.setPower(speed);
        }
        while (lowArm.isBusy() || upArm.isBusy()) {

        }
        lowArm.setPower(0);
        upArm.setPower(0);

        lowArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        upArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void climbDown() {
        lift1.setPosition(.82);
        lift2.setPosition(.82);
    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {
            newLeftTarget = lFDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rFDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

            lFDrive.setTargetPosition(newLeftTarget);
            rFDrive.setTargetPosition(newRightTarget);
            lRDrive.setTargetPosition(newLeftTarget);
            rRDrive.setTargetPosition(newRightTarget);

            lFDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rFDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runTime.reset();
            lFDrive.setPower(Math.abs(speed));
            rFDrive.setPower(Math.abs(speed));
            lRDrive.setPower(Math.abs(speed));
            rRDrive.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runTime.seconds() < timeoutS) &&
                    (lFDrive.isBusy() && rFDrive.isBusy())) {
                telemetry.addData("Path1", "Running to %7d: %7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d", lFDrive.getCurrentPosition(), rFDrive.getCurrentPosition());
                telemetry.update();
            }

            lFDrive.setPower(0);
            rFDrive.setPower(0);
            lRDrive.setPower(0);
            rRDrive.setPower(0);

            lFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}