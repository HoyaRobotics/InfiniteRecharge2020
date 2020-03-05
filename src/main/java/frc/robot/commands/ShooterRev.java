package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Limelight;
import frc.robot.util.Logger;

/**
 * This command revs up the shooter to the correct
 * RPM based on distance from the target (supplied
 * by the Limelight).
 * 
 * It is important that this is an entire command,
 * so that we can wait until the shooter is at the
 * correct speed before firing. This is opposed to
 * setting the RPM and then immediately firing before
 * the shooter is up to speed.
 */
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
        //                                                     ^
        // compensate for the fact that the Limelight is 6 inches in front
        // of the flywheel axle, which was the measuring point for developing
        // the RPM vs distance formula

        // If less than thirty feet away from the target, use the RPM vs distance
        // formula. Otherwise set RPM to constant speed.
        if(distance <= 30)
            targetRPM = (distance * 34.1) + 2505;
        else
            targetRPM = 3750;

        shooter.setFlywheelRPM(targetRPM);

        Logger.info("Started revving shooter to " + targetRPM + "RPM");
    }

    @Override
    public boolean isFinished(){
        return shooter.isStable();
    }

    @Override
    public void end(boolean interrupted){
        Logger.info("Finished revving shooter");
    }

}