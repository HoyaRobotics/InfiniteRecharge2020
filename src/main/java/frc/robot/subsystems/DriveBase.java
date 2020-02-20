package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class DriveBase extends SubsystemBase {
    
    private final WPI_TalonFX leftMaster = new WPI_TalonFX(FRONT_LEFT_DRIVE);
    private final WPI_TalonFX rightMaster = new WPI_TalonFX(FRONT_RIGHT_DRIVE);
    private final WPI_TalonFX leftSlave = new WPI_TalonFX(REAR_LEFT_DRIVE);
    private final WPI_TalonFX rightSlave = new WPI_TalonFX(REAR_RIGHT_DRIVE);

    private final DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

    public DriveBase(){
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
    }

    /**
     * Drives the robot with arcade controls, supplying throttle and rotation.
     */
    public void arcadeDrive(double throttle, double rotation){
        drive.arcadeDrive(throttle, rotation);
    }

}