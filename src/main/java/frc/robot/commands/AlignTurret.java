package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.Turret;
import frc.robot.util.Limelight;

public class AlignTurret extends CommandBase {

    private final Turret turret;
    private final Limelight limelight;

    public AlignTurret(Turret turret, Limelight limelight){
        this.turret = turret;
        this.limelight = limelight;
    }

    @Override
    public void execute(){
        turret.setRotatorSpeed(limelight.getXOffset() * TURRET_P);
    }

    @Override
    public boolean isFinished(){
        return (Math.abs(limelight.getXOffset()) < TURRET_SENSITIVITY_DEGREES)
            && (Math.abs(turret.getVelocity()) < TURRET_SENSITIVITY_VELOCITY);
    }

    @Override
    public void end(boolean interrupted){
        turret.setRotatorSpeed(0);
    }

}