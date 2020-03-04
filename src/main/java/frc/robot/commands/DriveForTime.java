package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveForTime extends CommandBase {

    private final DriveBase driveBase;
    private final double speed;

    private int counter = 0;
    private int target = 0;

    public DriveForTime(DriveBase driveBase, double speed, double seconds){
        this.driveBase = driveBase;
        this.speed = speed;

        target = (int)(seconds * 50);

        addRequirements(driveBase);
    }

    @Override
    public void execute(){
        if(counter < target)
            counter++;

        driveBase.arcadeDrive(-speed, 0);
    }

    @Override
    public boolean isFinished(){
        return counter >= target;
    }

    @Override
    public void end(boolean interrupted){
        driveBase.arcadeDrive(0, 0);
    }

}