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
        public static final int TOGGLE_INTAKE_RAISED = Button.kX.value;
        //Mct added
        public static final int TOGGLE_BALL_GATE = Button.kB.value;
        //end of McT added
        public static final int RUN_INTAKE = -1;
        public static final int SHOOT_BALL = -1;
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
    public static final int CLIMBER_LOCK = 0;
    public static final int SHIFTER = 1;
    public static final int BALL_GATE = 4; //port 2 is broken - do not use port 2.
    public static final int INTAKE_RAISER = 3;

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
    public static final double SHOOTER_P = 0;
    public static final double SHOOTER_I = 0;
    public static final double SHOOTER_IZ = 0;
    public static final double SHOOTER_D = 0;
    public static final double SHOOTER_FF = 0;
    public static final double RPM_STABILITY_ERROR = 25;
    public static final double SHOOTER_DISTANCE_SENSITVITY = 0;

    // Turret
    public static final double TURRET_P = 0.05;
    public static final double TURRET_SENSITIVITY_DEGREES = 0.5;
    public static final double TURRET_SENSITIVITY_VELOCITY = 0;

    // Limelight
    public static final double LL_HEIGHT_ABOVE_GROUND = 2.75;//in feet (2'9")
    public static final double LL_TARGET_HEIGHT = 7.5; //in feet
    public static final double LL_SHOT_HEIGHT = LL_TARGET_HEIGHT - LL_HEIGHT_ABOVE_GROUND;

}
