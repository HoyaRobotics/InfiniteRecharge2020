package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.util.Utils;

public class DriveWithJoystick extends CommandBase{
    
    private final DriveBase driveBase;
    private final DoubleSupplier throttle;
    private final DoubleSupplier rotation;

    public DriveWithJoystick(DriveBase driveBase, DoubleSupplier throttle, DoubleSupplier rotation){
        this.driveBase = driveBase;
        this.throttle = throttle;
        this.rotation = rotation;
        
        addRequirements(driveBase);
    }

    @Override
    public void execute() {
        driveBase.arcadeDrive(
            Utils.applyDeadband(throttle.getAsDouble(), Constants.CONTROL_DEADBAND),
            Utils.applyDeadband(rotation.getAsDouble(), Constants.CONTROL_DEADBAND)
        );
        SmartDashboard.putNumber("encoderUnits", driveBase.getPosition());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted){
        driveBase.arcadeDrive(0, 0);
    }
}