package frc.robot.util;

/**
 * This class supplies several utility functions for
 * clamping values to acceptable ranges.
 */
public class Utils{

    /**
     * Limit value from -1.0 to 1.0.
     */
    public static double limit(double value){
        if(value <= -1.0)
            return -1.0;
        else if(value >= 1.0)
            return 1.0;
        else
            return value;
    }

    /**
     * Treat values within specified deadband as zero.
     */
    public static double applyDeadband(double value, double deadband){
        if(Math.abs(value) < deadband)
            return 0.0;
        else
            return value;
    }

}