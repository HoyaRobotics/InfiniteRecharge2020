package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Limelight;
import frc.robot.util.Logger;

public class ShooterRev extends CommandBase {

    private final Shooter shooter;
    private final Limelight limelight;

    private double targetRPM = 0;

    public ShooterRev(Shooter shooter, Limelight limelight){
        this.shooter = shooter;
        this.limelight = limelight;

        addRequirements(shooter, limelight);
    }

    @Override
    public void initialize(){
        double distance = limelight.getDistanceFromTarget() + 0.5;

        //sets rpm
        if(distance <= 30)
            targetRPM = (distance * 34.1) + 2505;
        else
            targetRPM = 3750;

        shooter.setFlywheelRPM(targetRPM);
    }

    @Override
    public void execute(){
        Logger.info("Flywheel Velocity: " + shooter.getFlywheelRPM());
    }

    @Override
    public boolean isFinished(){
        return shooter.isStable();
    }

}