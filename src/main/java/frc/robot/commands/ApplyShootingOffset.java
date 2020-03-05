package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ApplyShootingOffset extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier commander;

    private long lastChange;

    public ApplyShootingOffset(Shooter shooter, DoubleSupplier commander){
        this.shooter = shooter;
        this.commander = commander;
    }

    @Override
    public void execute(){
        int command = (int) commander.getAsDouble();

        if(System.currentTimeMillis() - lastChange < 200)
            return;

        if(command == 0){
            shooter.incrementRPMOffset(100);
            lastChange = System.currentTimeMillis();
        }else if(command == 180){
            shooter.decrementRPMOffset(100);
            lastChange = System.currentTimeMillis();
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}