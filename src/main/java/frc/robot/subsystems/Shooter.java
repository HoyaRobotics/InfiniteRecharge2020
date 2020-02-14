package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {

    private final CANSparkMax left = new CANSparkMax(SHOOTER_LEFT, MotorType.kBrushless);
    private final CANSparkMax right = new CANSparkMax(SHOOTER_RIGHT, MotorType.kBrushless);

    private final CANPIDController leftPID = left.getPIDController();
    private final CANPIDController rightPID = right.getPIDController();

    private final CANEncoder leftEncoder = left.getEncoder();
    private final CANEncoder rightEncoder = right.getEncoder();

    public Shooter(){
        leftPID.setFeedbackDevice(leftEncoder);
        leftPID.setOutputRange(-1, 1);
        leftPID.setP(SHOOTER_P);
        leftPID.setI(SHOOTER_I);
        leftPID.setD(SHOOTER_D);
        leftPID.setIZone(SHOOTER_IZ);
        leftPID.setFF(SHOOTER_FF);

        rightPID.setFeedbackDevice(rightEncoder);
        rightPID.setOutputRange(-1, 1);
        rightPID.setP(SHOOTER_P);
        rightPID.setI(SHOOTER_I);
        rightPID.setD(SHOOTER_D);
        rightPID.setIZone(SHOOTER_IZ);
        rightPID.setFF(SHOOTER_FF);
    }

    public void setFlywheelSpeed(double speed){
        leftPID.setReference(speed, ControlType.kVelocity);
        rightPID.setReference(speed, ControlType.kVelocity);
    }

}