package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Logger;
import frc.robot.util.Utils;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This subsystem encapsulates the polycord systems used
 * to intake power cells.
 * 
 * It is generally seperated into two parts: external and internal.
 * The external part can be raised and lowered outside of the frame
 * perimeter during operation.
 * The internal part is the conveyor system leading the the shooter.
 */
public class Intake extends SubsystemBase {

    private final Solenoid raiser = new Solenoid(INTAKE_RAISER);
    
    private final CANSparkMax externalRoller = new CANSparkMax(INTAKE_EXTERNAL_ROLLER, MotorType.kBrushless);

    private final WPI_TalonSRX internalRollerMaster = new WPI_TalonSRX(INTAKE_INTERNAL_ROLLER_MASTER);
    private final WPI_TalonSRX internalRollerSlave = new WPI_TalonSRX(INTAKE_INTERNAL_ROLLER_SLAVE);

    private boolean raised = false;

    public Intake(){
        internalRollerSlave.follow(internalRollerMaster);
    }

    public void setRaised(boolean raised){
        if(this.raised != raised){
            this.raised = raised;
            
            // This is inverted because solenoid off = raised.
            raiser.set(!raised);

            if(raised)
                Logger.info("Raising intake");
            else
                Logger.info("Lowering intake");
        }
    }

    public void toggleRaised(){
        setRaised(!raised);
    }

    public void setExternalRoller(double speed){
        externalRoller.set(Utils.limit(speed));
    }

    public void setInternalRoller(double speed){
        internalRollerMaster.set(ControlMode.PercentOutput, speed);
    }

}