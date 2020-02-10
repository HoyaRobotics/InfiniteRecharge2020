package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class contains all the constant values used for setting up
 * and running the robot.
 */
public class Constants {

    // Controls
    public static final int CONTROLLER = 0;
    public static final Hand DRIVE = Hand.kLeft;
    public static final int TOGGLE_GEAR = Button.kY.value;

    // Drive
    public static final double CONTROL_DEADBAND = 0.15;
    public static final int FRONT_LEFT_DRIVE = 0;
    public static final int FRONT_RIGHT_DRIVE = 1;
    public static final int REAR_LEFT_DRIVE = 2;
    public static final int REAR_RIGHT_DRIVE = 3;
    public static final int SHIFTER = 0; //PCM id

}
