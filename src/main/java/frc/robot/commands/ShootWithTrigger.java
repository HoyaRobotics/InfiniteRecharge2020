package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

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