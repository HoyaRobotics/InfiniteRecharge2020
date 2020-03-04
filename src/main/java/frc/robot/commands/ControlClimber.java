package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ControlClimber extends CommandBase {

    private final Climber climber;
    private final DoubleSupplier commander;

    private long lastTrigger = 0;

    public ControlClimber(Climber climber, DoubleSupplier commander){
        this.climber = climber;
        this.commander = commander;

        addRequirements(climber);
    }

    @Override
    public void execute(){
        int command = (int) commander.getAsDouble();

        //up
        if(command == 0){
            if(climber.isLocked()){
                climber.setLocked(false);
                lastTrigger = System.currentTimeMillis();
            }
            if(System.currentTimeMillis() - lastTrigger > 200)
                climber.setVerticalSpeed(0.5);
        }
        //down
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
                lastTrigger = System.currentTimeMillis();
            }
            if(System.currentTimeMillis() - lastTrigger > 200)
                climber.setLocked(true);
        }

        //right
        if(command == 90){
            climber.setHorizontalSpeed(0.5);
        }
        //left
        else if(command == 270){
            climber.setHorizontalSpeed(-0.5);
        }else{
            climber.setHorizontalSpeed(0);
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}