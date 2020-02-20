package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Turret extends SubsystemBase {

    private final WPI_TalonSRX rotator = new WPI_TalonSRX(TURRET_ROTATOR);

    public Turret(){
        rotator.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.Analog, 0, 10);
        rotator.setSelectedSensorPosition(0, 0, 10);

        rotator.config_kF(0, TURRET_F);
        rotator.config_kP(0, TURRET_P);
        rotator.config_kI(0, TURRET_I);
        rotator.config_kD(0, TURRET_D);
    }

    public void newTarget(double degrees){
        rotator.setSelectedSensorPosition(0);
        rotator.set(ControlMode.Position, degrees);
    }

    public void updateTarget(double degrees){
        rotator.set(ControlMode.Position, degrees);
    }

    public double getVelocity(){
        return rotator.getSelectedSensorVelocity();
    }

}