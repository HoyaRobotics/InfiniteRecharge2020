package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class contains all the constant values used for setting up
 * and running the robot.
 */
public class Constants {

    public static class Controls {
        public static final Hand DRIVE = Hand.kLeft;
        public static final int TOGGLE_GEAR = Button.kY.value;
        public static final int TOGGLE_INTAKE_RAISED = -1;
        public static final int RUN_INTAKE = -1;
    }

    // USB ids
    public static final int CONTROLLER = 0;

    // CANbus ids
    public static final int FRONT_LEFT_DRIVE = 0;
    public static final int FRONT_RIGHT_DRIVE = 1;
    public static final int REAR_LEFT_DRIVE = 2;
    public static final int REAR_RIGHT_DRIVE = 3;
    public static final int INTAKE_EXTERNAL_ROLLER = -1;
    public static final int INTAKE_INTERNAL_ROLLER_MASTER = -1;
    public static final int INTAKE_INTERNAL_ROLLER_SLAVE = -1;
    public static final int SHOOTER_LEFT = -1;
    public static final int SHOOTER_RIGHT = -1;
    public static final int TURRET_ROTATOR = -1;

    // PCM ids
    public static final int SHIFTER = 0;
    public static final int INTAKE_RAISER = -1;
    public static final int BALL_GATE = -1;

    // DriveBase
    public static final double CONTROL_DEADBAND = 0.15;

    // Intake
    public static final double INTAKE_SPEED = 0.2;
    public static final double BALL_RELEASE_TIME = 0.1;

    // Shooter
    public static final double SHOOTER_P = 0;
    public static final double SHOOTER_I = 0;
    public static final double SHOOTER_IZ = 0;
    public static final double SHOOTER_D = 0;
    public static final double SHOOTER_FF = 0;
    public static final double RPM_STABILITY_ERROR = 0;
    public static final double SHOOTER_DISTANCE_SENSITVITY = 0;

    // Turret
    public static final double TURRET_P = 0;
    public static final double TURRET_I = 0;
    public static final double TURRET_D = 0;
    public static final double TURRET_F = 0;
    public static final double TURRET_SENSITIVITY_DEGREES = 0;
    public static final double TURRET_SENSITIVITY_VELOCITY = 0;

    // Limelight
    public static final double LL_HEIGHT_ABOVE_GROUND = 0;
    public static final double LL_TARGET_HEIGHT = 0;
    public static final double LL_SHOT_HEIGHT = LL_TARGET_HEIGHT - LL_HEIGHT_ABOVE_GROUND;

}
