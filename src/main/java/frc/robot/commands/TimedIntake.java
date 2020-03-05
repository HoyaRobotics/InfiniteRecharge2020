package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

/**
 * This command runs the intake at a certain speed
 * for a certain time.
 * 
 * There is also a distinction made between the internal
 * and external intakes.
 * The internal intake is the conveyor belt directly feeding
 * the shooter, while the external intake is the component
 * that extends outside of the robot frame during matches.
 * 
 * This is used for automatic ball control during shooting,
 * in teleop and autonomous.
 */
public class TimedIntake extends CommandBase{

    public enum IntakeMode {
        INTERNAL, EXTERNAL, BOTH
    }

    private final Intake intake;
    private final double speed;
    private final IntakeMode mode;
    private final int calls;

    private int callCounter = 0;

    public TimedIntake(Intake intake, double speed, double seconds, IntakeMode mode){
        this.intake = intake;
        this.speed = speed;
        this.mode = mode;

        calls = (int)(seconds * 50);
    }

    @Override
    public void initialize(){
        if(mode == IntakeMode.INTERNAL || mode == IntakeMode.BOTH)
            intake.setInternalRoller(speed);
        if(mode == IntakeMode.EXTERNAL || mode == IntakeMode.BOTH)
            intake.setExternalRoller(speed);

        callCounter = 1;
    }

    @Override
    public void execute(){
        callCounter++;
    }

    @Override
    public boolean isFinished(){
        return callCounter >= calls;
    }

    @Override
    public void end(boolean interrupted){
        intake.setInternalRoller(0);
        intake.setExternalRoller(0);
    }

}