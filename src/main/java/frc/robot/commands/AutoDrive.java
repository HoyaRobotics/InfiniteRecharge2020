package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.DriveBase;

/*
Constants to find:
INCHES_PER_UNIT
- find units per wheel rotation
- find circumference of wheel
- calculate

ACCEL_UNITS
- set to accelerate until set percent.
- if happy, analyse log to calculate

DECEL_UNITS
- accelerate, drive for a few seconds, then decel at rate
- if happy, analyse log to calculate
*/

public class AutoDrive extends CommandBase {

    private enum DriveMode { ACCEL, CRUISE, DECEL };

    private final DriveBase driveBase;
    private final double unitsTotal, unitsAccel, unitsCruise, unitsDecel;

    private DriveMode mode;
    private double percent;
    private double unitsTravelled;

    public AutoDrive(DriveBase driveBase, double distance){
        this.driveBase = driveBase;

        unitsTotal = distance /* / INCHES_PER_UNIT */;

        if(unitsTotal > ACCEL_UNITS + DECEL_UNITS){
            unitsAccel = ACCEL_UNITS;
            unitsDecel = DECEL_UNITS;
            unitsCruise = unitsTotal - (unitsAccel + unitsDecel);
        }else{
            unitsAccel = unitsTotal / 2;
            unitsDecel = unitsTotal - unitsAccel;
            unitsCruise = 0;
        }

        addRequirements(driveBase);
    }

    @Override
    public void initialize(){
        driveBase.zeroEncoders();
        mode = DriveMode.ACCEL;
        unitsTravelled = 0;
        percent = 0.25;
    }

    final double MAX_SPEED = 1.0;

    @Override
    public void execute(){
        switch(mode){
            case ACCEL:
                if(unitsTravelled < unitsAccel)
                    percent += 0.008;
                else
                    mode = DriveMode.CRUISE;
                break;
            case CRUISE:
                if(unitsTravelled > unitsAccel + unitsCruise)
                    mode = DriveMode.DECEL;
                break;
            case DECEL:
                if(driveBase.getVelocity() > 0)
                    percent -= 0.008;
                break;
        }

        if(percent > MAX_SPEED)
            percent = MAX_SPEED;
        else if(percent < 0)
            percent = 0;

        unitsTravelled = driveBase.getPosition();
        driveBase.arcadeDrive(-percent, 0);
    }

    @Override
    public boolean isFinished(){
        return unitsTravelled > 0 && driveBase.getVelocity() == 0;
    }

    @Override
    public void end(boolean interrupted){
        //Logger.info("Reached cruise speed at " + unitsTravelled + " units travelled.");
        driveBase.arcadeDrive(0, 0);
    }

}