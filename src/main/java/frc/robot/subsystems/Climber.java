package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Climber extends SubsystemBase {

    private final WPI_TalonSRX moveVertical = new WPI_TalonSRX(CLIMBER_VERTICAL);
    private final WPI_TalonSRX moveHorizontal = new WPI_TalonSRX(CLIMBER_HORIZONTAL);
    private final Solenoid lock = new Solenoid(CLIMBER_LOCK);

    public void setVerticalSpeed(double speed){
        moveVertical.set(speed);
    }

    public void setHorizontalSpeed(double speed){
        moveHorizontal.set(speed);
    }

    public void setLock(boolean on){
        lock.set(on);
    }

}