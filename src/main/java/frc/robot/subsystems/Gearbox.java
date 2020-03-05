package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Logger;

import static frc.robot.Constants.*;

/**
 * This small subsystem encapsulates our shifting gearboxes.
 * 
 * This was seperated from the DriveBase subsystem to ensure
 * constant control of robot motion, even when shifting gears.
 */
public class Gearbox extends SubsystemBase {

    private final Solenoid shifter = new Solenoid(SHIFTER);
    
    private boolean highGear = false;

    public void setHighGear(boolean highGear){
        if(highGear != this.highGear){
            this.highGear = highGear;
            shifter.set(!highGear);

            if(highGear)
                Logger.info("Shifting to high gear");
            else
                Logger.info("Shifting to low gear");
        }
    }

    public void toggleGear(){
        setHighGear(!highGear);
    }

    public boolean isHighGear(){
        return highGear;
    }

}