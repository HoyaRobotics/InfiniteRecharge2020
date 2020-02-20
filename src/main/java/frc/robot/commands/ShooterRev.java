package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Limelight;

public class ShooterRev extends CommandBase {

    private final Shooter shooter;
    private final Limelight limelight;

    private double targetRPM = 0;

    public ShooterRev(Shooter shooter, Limelight limelight){
        this.shooter = shooter;
        this.limelight = limelight;
    }

    @Override
    public void execute(){
        //fancy math based on distance to target
        int newTargetRPM = 0;

        if(Math.abs(newTargetRPM - targetRPM) > SHOOTER_DISTANCE_SENSITVITY){
            targetRPM = newTargetRPM;
            shooter.setFlywheelRPM(newTargetRPM);
        }
    }

    @Override
    public boolean isFinished(){
        return shooter.isStableAt(targetRPM);
    }

}