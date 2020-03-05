package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Turret extends SubsystemBase {

    private final WPI_TalonSRX rotator = new WPI_TalonSRX(TURRET_ROTATOR);

    public Turret(){
        rotator.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.Analog, 0, 10);
        rotator.setSelectedSensorPosition(0, 0, 10);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("turretRotation", rotator.getSelectedSensorPosition());
    }

    public void setRotatorSpeed(double speed){
        rotator.set(ControlMode.PercentOutput, speed);
    }

    public double getVelocity(){
        return rotator.getSelectedSensorVelocity();
    }

}