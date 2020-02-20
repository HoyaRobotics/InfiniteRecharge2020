package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ReleaseBall extends CommandBase {

    private final Intake intake;
    private final Shooter shooter;

    private double startTime;

    public ReleaseBall(Intake intake, Shooter shooter){
        this.intake = intake;
        this.shooter = shooter;
    }

    @Override
    public void initialize(){
        startTime = Timer.getFPGATimestamp();
        shooter.openBallGate();
        intake.setInternalRoller(0.1);
    }

    @Override
    public boolean isFinished(){
        return (Timer.getFPGATimestamp() - startTime) > BALL_RELEASE_TIME;
    }

    @Override
    public void end(boolean interrupted){
        shooter.closeBallGate();
        intake.setInternalRoller(0);
    }

}