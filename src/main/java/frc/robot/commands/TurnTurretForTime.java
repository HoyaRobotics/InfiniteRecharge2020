package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

/**
 * This command allows the turret to spin at a certain
 * speed for a certain time.
 * 
 * This is only used in autonomous.
 */
public class TurnTurretForTime extends CommandBase {

    private final Turret turret;
    private final double speed;

    private int counter = 0;
    private int target = 0;

    public TurnTurretForTime(Turret turret, double speed, double seconds){
        this.turret = turret;
        this.speed = speed;

        target = (int)(seconds * 50);

        addRequirements(turret);
    }

    @Override
    public void execute(){
        if(counter < target)
            counter++;

        turret.setRotatorSpeed(speed);
    }

    @Override
    public boolean isFinished(){
        return counter >= target;
    }

    @Override
    public void end(boolean interrupted){
        turret.setRotatorSpeed(0);
    }

}