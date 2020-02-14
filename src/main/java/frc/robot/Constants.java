package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class contains all the constant values used for setting up
 * and running the robot.
 */
public class Constants {

    // Controls
    public static final Hand DRIVE = Hand.kLeft;
    public static final int TOGGLE_GEAR = Button.kY.value;
    public static final int TOGGLE_INTAKE_RAISED = -1;
    public static final int RUN_INTAKE = -1;

    // USB ids
    public static final int CONTROLLER = 0;

    // CANbus ids
    public static final int FRONT_LEFT_DRIVE = 0;
    public static final int FRONT_RIGHT_DRIVE = 1;
    public static final int REAR_LEFT_DRIVE = 2;
    public static final int REAR_RIGHT_DRIVE = 3;
    public static final int INTAKE_POLYCORD_ROLLER = -1;

    // PCM ids
    public static final int SHIFTER = 0;
    public static final int INTAKE_RAISER = -1;

    // DriveBase
    public static final double CONTROL_DEADBAND = 0.15;

    // Intake
    public static final double INTAKE_SPEED = 0.2;

}
