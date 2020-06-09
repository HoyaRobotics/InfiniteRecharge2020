package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * This subsystem encapsulates the Limelight camera and
 * its NetworkTables interface.
 * 
 * The Limelight is integral to our automated shooting system.
 * It provides both the horizontal offset for turret alignment
 * and distance to target for shooter revving.
 */
public class Limelight extends SubsystemBase{

    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    private final NetworkTableEntry pipeline = table.getEntry("pipeline");
    private final NetworkTableEntry xOffset = table.getEntry("tx");
    private final NetworkTableEntry yOffset = table.getEntry("ty");

    private int currentPipeline = 0;

    @Override
    public void periodic(){
        table.getEntry("ledMode").setNumber(3);
    }

    /**
     * Toggles between two pipelines identical
     * other than one is 2x zoomed.
     */
    public void toggleZoom(){
        if(currentPipeline == 0)
            currentPipeline = 1;
        else if(currentPipeline == 1)
            currentPipeline = 0;

        setPipeline(currentPipeline);
    }

    /**
     * Calculates distance to target using techniques from:
     * https://docs.limelightvision.io/en/latest/cs_estimating_distance.html#fixed-angle-camera
     * @return distance to target
     */
    public double getDistanceFromTarget(){
        return Constants.LL_SHOT_HEIGHT / Math.abs(Math.toRadians(getYOffset()));
    }

    public double getXOffset(){
        return xOffset.getDouble(0);
    }

    public double getYOffset(){
        return yOffset.getDouble(0);
    }

    public void setPipeline(int id){
        pipeline.setNumber(id);
    }
}