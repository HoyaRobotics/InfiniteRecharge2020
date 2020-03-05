package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * This command allows the shooter's speed to be directly
 * controlled with a joystick trigger.
 * 
 * Manual control systems such as this are important in case
 * of Limelight failure (in which case our entire shooting
 * capability otherwise disappears).
 */
public class ShootWithTrigger extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier speed;

    public ShootWithTrigger(Shooter shooter, DoubleSupplier speed){
        this.shooter = shooter;
        this.speed = speed;

        addRequirements(shooter);
    }

    @Override
    public void execute(){
        shooter.setFlywheelPercent(speed.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted){
        shooter.setFlywheelPercent(0);
    }

}