package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
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
        leftMaster.configFactoryDefault();
        rightMaster.configFactoryDefault();
        leftSlave.configFactoryDefault();
        rightSlave.configFactoryDefault();

        SupplyCurrentLimitConfiguration supplyLimit = new SupplyCurrentLimitConfiguration(true, 30, 35, 1.0);
        leftMaster.configSupplyCurrentLimit(supplyLimit);
        rightMaster.configSupplyCurrentLimit(supplyLimit);
        leftSlave.configSupplyCurrentLimit(supplyLimit);
        rightSlave.configSupplyCurrentLimit(supplyLimit);

        leftMaster.setNeutralMode(NeutralMode.Coast);
        rightMaster.setNeutralMode(NeutralMode.Coast);
        leftSlave.setNeutralMode(NeutralMode.Coast);
        rightSlave.setNeutralMode(NeutralMode.Coast);

        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        zeroEncoders();
    }

    public void zeroEncoders(){
        leftMaster.getSensorCollection().setIntegratedSensorPosition(0, 30);
        rightMaster.getSensorCollection().setIntegratedSensorPosition(0, 30);
    }

    public double getPosition(){
        return 0;
        //return rightMaster.getSensorCollection().getIntegratedSensorPosition();
    }

    public double getVelocity(){
        return 0;
        //return rightMaster.getSensorCollection().getIntegratedSensorVelocity();
    }

    /**
     * Drives the robot with arcade controls, supplying throttle and rotation.
     */
    public void arcadeDrive(double throttle, double rotation){
        rotation *= -1;

        drive.arcadeDrive(throttle, rotation);
    }

}