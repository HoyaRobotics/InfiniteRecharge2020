package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.Turret;
import frc.robot.util.Logger;
import frc.robot.subsystems.Limelight;

//for later autonomous: 1.2deg offset
public class AlignTurret extends CommandBase {

    private final Turret turret;
    private final Limelight limelight;

    public AlignTurret(Turret turret, Limelight limelight){
        this.turret = turret;
        this.limelight = limelight;
        
        addRequirements(turret, limelight);
    }

    @Override
    public void initialize(){
        Logger.info("Beginning turret alignment");
    }

    @Override
    public void execute(){
        //POSITVE ROTATOR SPEED = CLOCKWISE
        //NEGATIVE ROTATOR SPEED = COUNTER-CLOCKWISE
        double speed = (limelight.getXOffset() + 2) * TURRET_P;

        if(speed > 0.1)
            speed = 0.1;
        else if(speed < -0.1)
            speed = -0.1;

        turret.setRotatorSpeed(speed);
    }

    @Override
    public boolean isFinished(){
        return Math.abs((limelight.getXOffset() + 2)) < TURRET_SENSITIVITY_DEGREES;
    }

    @Override
    public void end(boolean interrupted){
        turret.setRotatorSpeed(0);
        Logger.info("Finished turret alignment");
    }

}