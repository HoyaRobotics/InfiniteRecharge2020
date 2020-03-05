package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class contains all the constant values used for setting up
 * and running the robot.
 */
public class Constants {

    public static class Controls {
        // Driver controls:
        public static final Hand DRIVE = Hand.kLeft;
        public static final int SHIFT_GEAR = Button.kY.value;
        public static final int RUN_INTAKE_FWD = Button.kBumperRight.value;
        public static final int RUN_INTAKE_RVS = Button.kBumperLeft.value;
        public static final int TOGGLE_INTAKE_RAISED = Button.kX.value;
        public static final int TOGGLE_BALL_GATE = Button.kB.value;
        public static final int SHOOT_BALL = Button.kA.value;
        public static final int SHOOT_BALL_MANUAL = 7;

        // Operator controls:
        public static final Hand ROTATE_TURRET = Hand.kLeft;
        public static final int TOGGLE_LIMELIGHT_ZOOM = Button.kB.value;
        public static final int INC_RPM_OFFSET = Button.kBumperRight.value;
        public static final int DEC_RPM_OFFSET = Button.kBumperLeft.value;
    }

    // USB ids
    public static final int DRIVER = 0;
    public static final int OPERATOR = 1;

    // CANbus ids
    public static final int FRONT_LEFT_DRIVE = 0;
    public static final int FRONT_RIGHT_DRIVE = 1;
    public static final int REAR_LEFT_DRIVE = 2;
    public static final int REAR_RIGHT_DRIVE = 3;
    public static final int TURRET_ROTATOR = 4;
    public static final int INTAKE_INTERNAL_ROLLER_MASTER = 5;
    public static final int INTAKE_INTERNAL_ROLLER_SLAVE = 6;
    public static final int INTAKE_EXTERNAL_ROLLER = 7;
    public static final int SHOOTER_LEFT = 8;
    public static final int SHOOTER_RIGHT = 9;
    public static final int CLIMBER_VERTICAL_1 = 10;
    public static final int CLIMBER_VERTICAL_2 = 11;
    public static final int CLIMBER_HORIZONTAL = 12;

    // PCM ids
    // (our PCM's port 2 is broken, do not use it)
    public static final int CLIMBER_LOCK = 0;
    public static final int SHIFTER = 1;
    public static final int INTAKE_RAISER = 3;
    public static final int BALL_GATE = 4;

    // DriveBase
    public static final double CONTROL_DEADBAND = 0.10;
    public static final int SENSOR_UNITS_PER_ROTATION = 2048;
    public static final int ENCODER_UNITS_PER_ROTATION = 24140;
    public static final int ACCEL_UNITS = 473000;
    public static final int DECEL_UNITS = ACCEL_UNITS;

    // Intake
    public static final double INTAKE_SPEED = 0.2;
    public static final double BALL_RELEASE_TIME = 0.1;

    // Shooter
    public static final double SHOOTER_P = 0.0003;
    public static final double SHOOTER_FF = 0.00019;
    public static final double RPM_STABILITY_ERROR = 25;

    // Turret
    public static final double TURRET_P = 0.05;
    public static final double TURRET_SENSITIVITY_DEGREES = 0.5;
    public static final double TURRET_SENSITIVITY_VELOCITY = 0;

    // Limelight
    public static final double LL_HEIGHT_ABOVE_GROUND = 2.75;// in feet (2'9")
    public static final double LL_TARGET_HEIGHT = 7.5; // in feet (7'6")
    public static final double LL_SHOT_HEIGHT = LL_TARGET_HEIGHT - LL_HEIGHT_ABOVE_GROUND;

}
