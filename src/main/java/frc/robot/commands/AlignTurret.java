package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.Turret;
import frc.robot.util.Logger;
import frc.robot.subsystems.Limelight;

/**
 * This class is the command responsible for aligning the
 * turret towards the Limelight's target.
 * 
 * Before running this command, drivers should check the
 * Limelight's camera feed to ensure it is locked on to
 * the correct target.
 */
public class AlignTurret extends CommandBase {

    private final Turret turret;
    private final Limelight limelight;

    private boolean useTDM = false;
    private int tdmCount = 0;
    private int tdmThreshold = 100;

    public AlignTurret(Turret turret, Limelight limelight){
        this(turret, limelight, false);
    }

    public AlignTurret(Turret turret, Limelight limelight, boolean useTDM){
        this.turret = turret;
        this.limelight = limelight;
        this.useTDM = useTDM;
        
        addRequirements(turret, limelight);
    }

    @Override
    public void initialize(){
        Logger.info("Beginning turret alignment");
    }

    @Override
    public void execute(){
        // Positive speed = Clockwise motion
        // Negative speed = Counter-clockwise motion

        double speed = (limelight.getXOffset() + 2) * TURRET_P;
        //                                       ^
        //offset target 2 degrees to the right (this seems to line it up nicer)

        // Limit speed to 10% motor speed in either direction.
        // This should be adjusted later to find optimal rotating speed.
        if(speed > 0.1)
            speed = 0.1;
        else if(speed < -0.1)
            speed = -0.1;

        turret.setRotatorSpeed(speed);

        tdmCount++;
    }

    @Override
    public boolean isFinished(){
        // Apply T.D.M. (turret disaster mitigation) control
        if(useTDM && tdmCount >= tdmThreshold){
            Logger.warn("Turret disaster mitigation triggered");
            return true;
        }

        // Because the Limelight's x offset has some error while targeting,
        // don't wait for it to be exactly zero, but instead within an
        // acceptable error range.
        return Math.abs((limelight.getXOffset() + 2)) < TURRET_SENSITIVITY_DEGREES;
    }

    @Override
    public void end(boolean interrupted){
        turret.setRotatorSpeed(0);
        Logger.info("Finished turret alignment");
    }

}