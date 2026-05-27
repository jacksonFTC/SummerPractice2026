package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

@TeleOp(name = "Limelight Position Telemetry", group = "TeleOp")
public class testLimelight extends LinearOpMode {

    private Limelight3A limelight;

    @Override
    public void runOpMode() {

        // ── Initialize Limelight ──────────────────────────────────────────────
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        limelight.setPollRateHz(100);   // Poll at 100Hz
        limelight.pipelineSwitch(0);    // Use pipeline 0 (your AprilTag/MegaTag 1 setup)
        limelight.start();

        telemetry.addData("Status", "Limelight initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        // ── Main Loop ─────────────────────────────────────────────────────────
        while (opModeIsActive()) {

            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {

                // ── MegaTag 1 Robot Pose (field-space position) ───────────────
                Pose3D botpose = result.getBotpose();

                if (botpose != null) {
                    double x   = botpose.getPosition().x;  // meters, field X
                    double y   = botpose.getPosition().y;  // meters, field Y
                    double z   = botpose.getPosition().z;  // meters, height (usually ~0)

                    // Orientation from pose
                    double yaw   = botpose.getOrientation().getYaw();
                    double pitch = botpose.getOrientation().getPitch();
                    double roll  = botpose.getOrientation().getRoll();

                    telemetry.addLine("=== MegaTag 1 Robot Pose ===");
                    telemetry.addData("Field X (m)",  String.format("%.4f", x));
                    telemetry.addData("Field Y (m)",  String.format("%.4f", y));
                    telemetry.addData("Field Z (m)",  String.format("%.4f", z));
                    telemetry.addData("Yaw (deg)",    String.format("%.2f", yaw));
                    telemetry.addData("Pitch (deg)",  String.format("%.2f", pitch));
                    telemetry.addData("Roll (deg)",   String.format("%.2f", roll));

                } else {
                    telemetry.addLine("=== MegaTag 1 Robot Pose ===");
                    telemetry.addData("BotPose", "NULL - check Full 3D is enabled");
                }

                // ── Per-Tag Info ──────────────────────────────────────────────
                List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
                if (fiducials != null && !fiducials.isEmpty()) {
                    telemetry.addLine("");
                    telemetry.addLine("=== Visible AprilTags ===");
                    for (LLResultTypes.FiducialResult tag : fiducials) {
                        int    tagId    = tag.getFiducialId();
                        double tagX     = tag.getTargetXDegrees();
                        double tagY     = tag.getTargetYDegrees();
                        double tagArea  = tag.getTargetArea();
                        telemetry.addData(
                                "Tag " + tagId,
                                String.format("tx=%.1f° ty=%.1f° area=%.2f%%",
                                        tagX, tagY, tagArea)
                        );
                    }
                } else {
                    telemetry.addData("AprilTags", "None visible");
                }

                // ── Data Freshness ────────────────────────────────────────────
                telemetry.addLine("");
                long staleness = result.getStaleness();
                telemetry.addData("Data Age (ms)", staleness);
                telemetry.addData("Pipeline Index", result.getPipelineIndex());

            } else {
                telemetry.addLine("=== NO VALID RESULT ===");
                telemetry.addData("Check", "Pipeline 0 active? Tags in view?");
            }

            // ── Limelight Status ──────────────────────────────────────────────
            LLStatus status = limelight.getStatus();
            telemetry.addLine("");
            telemetry.addData("LL Temp (°C)", String.format("%.1f", status.getTemp()));
            telemetry.addData("LL FPS",       String.format("%.0f", status.getFps()));

            telemetry.update();
        }

        // ── Cleanup ───────────────────────────────────────────────────────────
        limelight.stop();
    }
}