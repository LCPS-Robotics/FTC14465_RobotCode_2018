package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Gold Auto", group="14465")
public class goldAuton extends LinearOpMode {
    /*
    1. Lower down from Lander
    2. Drive Backward
    2.5. Turn Left and Drive Forward
    3. Sample
    4. Turn Left
    5. Drive Forward
    6. Turn Right
    7. Drive to Depot
    8. Drop Marker (Like the Bass)
    9. Turn Left
    10. Go to the Crater
    11. (Optional) Pick up Elements
     */

    //leftDrive Motors
    private DcMotor lFDrive = null;
    private DcMotor lRDrive = null;

    //rightDrive Motors
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

    }

}
