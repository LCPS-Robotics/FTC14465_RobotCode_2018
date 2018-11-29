package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {

    }
}
