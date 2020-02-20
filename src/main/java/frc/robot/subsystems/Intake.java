package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Utils;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {

    private final Solenoid raiser = new Solenoid(INTAKE_RAISER);
    
    private final CANSparkMax polycordRoller = new CANSparkMax(INTAKE_POLYCORD_ROLLER, MotorType.kBrushless);

    private boolean raised = false;

    public void setRaised(boolean raised){
        if(this.raised != raised){
            this.raised = raised;
            
            //this is inverted because solenoid off = raised
            raiser.set(!raised);
        }
    }

    public void toggleRaised(){
        setRaised(!raised);
    }

    public void setPolycordRoller(double speed){
        polycordRoller.set(Utils.limit(speed));
    }

}