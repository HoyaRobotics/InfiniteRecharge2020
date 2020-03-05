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

    // Variable to keep track of the last time a command
    // was sent. This is used to create a small delay
    // between disengaging the lock and moving the elevator.
    private long lastTrigger = 0;

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
        if(command == 0){
            if(climber.isLocked()){
                climber.setLocked(false);
                lastTrigger = System.currentTimeMillis();
            }
            if(System.currentTimeMillis() - lastTrigger > 200)
                climber.setVerticalSpeed(0.5);
        }
        // Down
        else if(command == 180){
            if(climber.isLocked()){
                climber.setLocked(false);
                lastTrigger = System.currentTimeMillis();
            }
            if(System.currentTimeMillis() - lastTrigger > 200)
                climber.setVerticalSpeed(-0.5);
        }else{
            if(!climber.isLocked()){
                climber.setVerticalSpeed(0);
                climber.setLocked(true);
                lastTrigger = System.currentTimeMillis();
            }
        }

        // Leveler control:
        // (this is much shorter since there is no lock)
        // Right
        if(command == 90){
            climber.setHorizontalSpeed(1.0);
        }
        // Left
        else if(command == 270){
            climber.setHorizontalSpeed(-1.0);
        }else{
            climber.setHorizontalSpeed(0);
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}