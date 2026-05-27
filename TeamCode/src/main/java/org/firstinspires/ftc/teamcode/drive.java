package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class drive extends LinearOpMode {
    private DcMotor backRight;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor frontLeft;



    @Override
    public void runOpMode() {
        DcMotor bkLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor frLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor bkRight = hardwareMap.dcMotor.get("backRight");
        DcMotor frRight = hardwareMap.dcMotor.get("frontRight");

    }

}
