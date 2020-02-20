package frc.robot.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private static final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    private static final NetworkTableEntry xOffset = table.getEntry("tx");
    private static final NetworkTableEntry yOffset = table.getEntry("ty");

    public double getXOffset(){
        return xOffset.getDouble(0);
    }

    public double getYOffset(){
        return yOffset.getDouble(0);
    }
}