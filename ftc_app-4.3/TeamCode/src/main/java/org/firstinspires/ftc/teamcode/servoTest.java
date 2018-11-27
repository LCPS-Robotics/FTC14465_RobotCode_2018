package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo Test", group = "Sensors")
public class servoTest extends OpMode {

    private Servo bucket = null;

    @Override
    public void init() {
        telemetry.addData("Servo Angle", bucket.getPosition());
        bucket = hardwareMap.get(Servo.class, "bucketServo");
    }

    @Override
    public void start(){
        bucket.setPosition(.25);
    }

    @Override
    public void loop() {
        telemetry.addData("Servo Angle", bucket.getPosition());

        if(gamepad1.right_bumper){
            bucket.setPosition(.25);
        }
        else{

        }

        if(gamepad1.left_bumper){
            bucket.setPosition(1);
        }
        else {

        }

        telemetry.update();
    }
}
