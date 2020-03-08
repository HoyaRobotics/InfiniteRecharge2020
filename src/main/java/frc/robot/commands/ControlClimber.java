package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * This command is responsible for monitoring the
 * driver's d-pad and controlling the climber accordingly.
 * 
 * Up/down on the d-pad = up/down on the elevator
 * Left/right on the d-pad = left/right on the leveler
 * 
 * Because the elevator's lock is engaged by default, it
 * is automatically disengaged before any movement and then
 * re-enagated afterwords.
 */
public class ControlClimber extends CommandBase {

    private final Climber climber;
    private final DoubleSupplier commander;

    public ControlClimber(Climber climber, DoubleSupplier commander){
        this.climber = climber;
        this.commander = commander;

        addRequirements(climber);
    }

    @Override
    public void execute(){
        int command = (int) commander.getAsDouble();

        // Elevator control:
        // Up
        if(command == 0)
            climber.setVerticalSpeed(0.5);
        // Down
        else if(command == 180)
            climber.setVerticalSpeed(-0.5);
        else
            climber.setVerticalSpeed(0);

        // Leveler control:
        // Right
        if(command == 90)
            climber.setHorizontalSpeed(1.0);
        // Left
        else if(command == 270)
            climber.setHorizontalSpeed(-1.0);
        else
            climber.setHorizontalSpeed(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}