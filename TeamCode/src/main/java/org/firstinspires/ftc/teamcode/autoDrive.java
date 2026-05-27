/* intake positioning data from either limelight or odometry
do math to calculate position error
set motor speeds with pid and constants to drive to specified point based on position error
 */

package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;




public class autoDrive {
    Limelight3A limelight;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!
    }


    private LLResult result;
    Pose3D botpose = result.getBotpose();
            double posX = botpose.getPosition().x;
            double posY = botpose.getPosition().y;
            double posZ = botpose.getPosition().z;




    double desirePosX = //desired x
    double desirePosY = //desired y
    double desirePosZ = //desired angle
    double posErrX = desirePosX-posX
    double posErrY = desirePosY-posY
    double posErrZ = desirePosZ-posZ
    double relativeMovementX = posErrX*Math.cos(posZ)+posErrY*Math.sin(posZ)
    double relativeMovementY = -posErrY*Math.cos(posZ)+posErrX*Math.sin(posZ)



}
