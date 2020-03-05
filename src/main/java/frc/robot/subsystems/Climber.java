package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Logger;

import static frc.robot.Constants.*;

public class Climber extends SubsystemBase {

    private final WPI_TalonSRX moveHorizontal = new WPI_TalonSRX(CLIMBER_HORIZONTAL);
    private final WPI_TalonSRX moveVerticalMaster = new WPI_TalonSRX(CLIMBER_VERTICAL_1);
    private final WPI_VictorSPX moveVerticalSlave = new WPI_VictorSPX(CLIMBER_VERTICAL_2);
    private final Solenoid lock = new Solenoid(CLIMBER_LOCK);

    private boolean locked = true;

    public Climber(){
        moveVerticalSlave.follow(moveVerticalMaster);
    }

    public void setVerticalSpeed(double speed){
        moveVerticalMaster.set(speed);
    }

    public void setHorizontalSpeed(double speed){
        moveHorizontal.set(speed);
    }

    public void setLocked(boolean locked){
        this.locked = locked;
        lock.set(!locked);

        if(locked)
            Logger.info("Climber is locked");
        else
            Logger.info("Climber is unlocked");
    }

    public boolean isLocked(){
        return locked;
    }

}