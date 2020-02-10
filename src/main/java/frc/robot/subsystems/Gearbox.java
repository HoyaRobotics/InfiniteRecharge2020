package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Gearbox extends SubsystemBase {

    private final Solenoid shifter = new Solenoid(Constants.SHIFTER);
    
    private boolean highGear = false;

    public void setHighGear(boolean highGear){
        if(highGear != this.highGear){
            this.highGear = highGear;
            shifter.set(!highGear);
        }
    }

    public void toggleGear(){
        setHighGear(!highGear);
    }

}