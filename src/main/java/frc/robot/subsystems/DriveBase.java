package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Utils;

import static frc.robot.Constants.*;

public class DriveBase extends SubsystemBase {

    private final TalonFX frontLeftMotor = new TalonFX(FRONT_LEFT_DRIVE);
    private final TalonFX frontRightMotor = new TalonFX(FRONT_RIGHT_DRIVE);
    private final TalonFX rearLeftMotor = new TalonFX(REAR_LEFT_DRIVE);
    private final TalonFX rearRightMotor = new TalonFX(REAR_RIGHT_DRIVE);

    /**
     * Drives the robot with arcade controls, supplying throttle and rotation.
     */
    public void arcadeDrive(double throttle, double rotation){
        double leftSide = Utils.limit((throttle * -1) + rotation);
        double rightSide = Utils.limit(throttle + rotation);

        frontLeftMotor.set(ControlMode.PercentOutput, leftSide);
        rearLeftMotor.set(ControlMode.PercentOutput, leftSide);
        frontRightMotor.set(ControlMode.PercentOutput, rightSide);
        rearRightMotor.set(ControlMode.PercentOutput, rightSide);
    }

}