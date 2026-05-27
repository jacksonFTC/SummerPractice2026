package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.SerialNumber;


public class mainSummer extends LinearOpMode {
    private DcMotor backRight ;
    private DcMotor backLeft  ;
    private DcMotor frontLeft ;
    private DcMotor frontRight ;

    DcMotor bkRight = hardwareMap.get (DcMotor.class, (SerialNumber) backRight);
    DcMotor bkLeft = hardwareMap.get (DcMotor .class, (SerialNumber) backLeft);
    DcMotor frLeft = hardwareMap.get (DcMotor.class, (SerialNumber) frontLeft) ;
    DcMotor frRight = hardwareMap.get (DcMotor.class, (SerialNumber) frontRight) ;

    double strafe = gamepad1.left_stick_x ;
    double forward = gamepad1.left_stick_y ;
    double turn = gamepad1.right_stick_x ;

   private void drive () {
           frRight.setPower((forward - (strafe - turn)) );
            bkRight.setPower((forward + strafe + turn) );
            bkLeft.setPower((forward + (strafe - turn)) );
            frLeft.setPower((forward - (strafe + turn)) );

       public void runOpMode() {



           // Put initialization blocks here.

           telemetry.addData("Status:", "Ready");
           telemetry.update();
           waitForStart();
           if (opModeIsActive()) {
               // Put run blocks here.
               while (opModeIsActive()) {
                   // Put loop blocks here.
                   drive();

               }
           }
       }
}



}
