package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
public class oldDriveCode extends LinearOpMode{
@TeleOp(name = "DecodeTeleA22 (Blocks to Java)")



        private DcMotor launch;
        private Servo arm;
        private DcMotor frRight;
        private DcMotor bkRight;
        private DcMotor bkLeft;
        private DcMotor frLeft;
        private DcMotor intake;
        private CRServo transfer;

        int launcherToggle;
        double speed;
        double launcherSpeed;
        String LaunchTelemetry;

        /**
         * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
         * Comment Blocks show where to place Initialization code (runs once, after touching the
         * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
         * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
         * Stopped).
         */
        @Override
        public void runOpMode() {
            // TODO: Enter the type for variable named ArmTelemetry
            UNKNOWN_TYPE ArmTelemetry;
            int intakeVariable1;
            String IntakeTelemetry;

            launch = hardwareMap.get(DcMotor.class, "launchAsDcMotor");
            arm = hardwareMap.get(Servo.class, "armAsServo");
            frRight = hardwareMap.get(DcMotor.class, "frRightAsDcMotor");
            bkRight = hardwareMap.get(DcMotor.class, "bkRightAsDcMotor");
            bkLeft = hardwareMap.get(DcMotor.class, "bkLeftAsDcMotor");
            frLeft = hardwareMap.get(DcMotor.class, "frLeftAsDcMotor");
            intake = hardwareMap.get(DcMotor.class, "intakeAsDcMotor");
            transfer = hardwareMap.get(CRServo.class, "transferAsCRServo");

            // Put initialization blocks here.
            speed = 0.45;
            launcherToggle = 0;
            launcherSpeed = 0.523;
            intakeVariable1 = 0;
            IntakeTelemetry = "Normal";
            LaunchTelemetry = "Off";
            LaunchTelemetry = "Down";
            telemetry.addData("Status:", "Ready");
            telemetry.update();
            waitForStart();
            if (opModeIsActive()) {
                // Put run blocks here.
                while (opModeIsActive()) {
                    // Put loop blocks here.
                    drive();
                    launchArm();
                    intake2();
                    launchWheel();
                    telemetry.addData("Status:", "Running");
                    telemetry.addLine(" hmmm");
                    telemetry.addData("Launcher Power Target", launcherSpeed);
                    telemetry.addData("Launcher Motor Power", launch.getPower());
                    telemetry.addData("Launcher Mode", LaunchTelemetry);
                    telemetry.addData("Intake Mode", IntakeTelemetry);
                    telemetry.addData("Arm Position", ArmTelemetry);
                    telemetry.update();
                }
            }
        }

        /**
         * Describe this function...
         */
        private void drive() {
            double triggerSpeed;
            double Forward;
            double Strafe;
            double Turn;
            double DenomernaTERER;

            if (gamepad1.left_trigger > 0.05) {
                triggerSpeed = speed + 0.7 * gamepad1.left_trigger;
            } else {
                triggerSpeed = speed;
            }
            Forward = gamepad1.right_stick_x * -triggerSpeed;
            Strafe = gamepad1.left_stick_x * triggerSpeed;
            Turn = -gamepad1.left_stick_y * triggerSpeed;
            DenomernaTERER = JavaUtil.maxOfList(JavaUtil.createListWith(1, Math.abs(Forward) + Math.abs(Strafe) + Math.abs(Turn)));
            frRight.setPower((Forward - (Strafe - Turn)) / DenomernaTERER);
            bkRight.setPower((Forward + Strafe + Turn) / DenomernaTERER);
            bkLeft.setPower((Forward + (Strafe - Turn)) / DenomernaTERER);
            frLeft.setPower((Forward - (Strafe + Turn)) / DenomernaTERER);
        }

        /**
         * Describe this function...
         */
        private void launchArm() {
            while (gamepad1.a || gamepad2.a) {
                bkLeft.setPower(0);
                bkRight.setPower(0);
                frLeft.setPower(0);
                frRight.setPower(0);
                intake.setPower(-0.5);
                arm.setPosition(0.28);
                transfer.setPower(-0.2);
                sleep(600);
                transfer.setPower(1);
                arm.setPosition(0.04);
                sleep(600);
                transfer.setPower(-0.2);
                sleep(0);
            }
        }

        /**
         * Describe this function...
         */
        private void launchWheel() {
            double my_2B;

            if (launcherToggle == 0) {
                if (gamepad1.bWasReleased() || gamepad2.xWasReleased()) {
                    launcherToggle = 1;
                }
            } else {
                if (gamepad1.bWasReleased() || gamepad2.xWasReleased()) {
                    launcherToggle = 0;
                    launch.setPower(0);
                    LaunchTelemetry = "Off";
                }
            }
            if (gamepad2.b) {
                my_2B = 0.8;
            } else {
                my_2B = 0;
            }
            if (launcherToggle == 1) {
                launch.setPower(-(my_2B + launcherSpeed));
                LaunchTelemetry = "On";
            }
            if (gamepad1.leftBumperWasReleased() || gamepad2.leftBumperWasReleased()) {
                launcherSpeed += -0.025;
            }
            if (gamepad1.rightBumperWasReleased() || gamepad2.rightBumperWasReleased()) {
                launcherSpeed += 0.025;
            }
        }

        /**
         * Describe this function...
         */
        private void intake2() {
            intake.setPower(-(gamepad1.right_trigger + gamepad2.right_trigger));
            transfer.setPower(gamepad1.right_trigger + gamepad2.right_trigger + -gamepad2.left_stick_y);
        }
    }
}
