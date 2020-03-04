package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase{

    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    private final NetworkTableEntry pipeline = table.getEntry("pipeline");
    private final NetworkTableEntry xOffset = table.getEntry("tx");
    private final NetworkTableEntry yOffset = table.getEntry("ty");

    private int currentPipeline = 0;

    @Override
    public void periodic(){
        SmartDashboard.putNumber("limelightDistance", getDistanceFromTarget());
    }

    public void toggleZoom(){
        if(currentPipeline == 0)
            currentPipeline = 1;
        else if(currentPipeline == 1)
            currentPipeline = 0;

        setPipeline(currentPipeline);
    }

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