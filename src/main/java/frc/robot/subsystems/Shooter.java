package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {

    private final Solenoid ballGate = new Solenoid(BALL_GATE);

    private final CANSparkMax master = new CANSparkMax(SHOOTER_LEFT, MotorType.kBrushless);
    private final CANSparkMax slave = new CANSparkMax(SHOOTER_RIGHT, MotorType.kBrushless);

    private final CANPIDController pid = master.getPIDController();
    private final CANEncoder encoder = master.getEncoder();

    public Shooter(){
        pid.setFeedbackDevice(encoder);
        pid.setOutputRange(-1, 1);
        pid.setP(SHOOTER_P);
        pid.setI(SHOOTER_I);
        pid.setD(SHOOTER_D);
        pid.setIZone(SHOOTER_IZ);
        pid.setFF(SHOOTER_FF);

        slave.follow(master);
    }

    public void setFlywheelRPM(double rpm){
        pid.setReference(rpm, ControlType.kVelocity);
    }

    public double getFlywheelRPM(){
        return encoder.getVelocity();
    }

    public void openBallGate(){
        ballGate.set(true);
    }

    public void closeBallGate(){
        ballGate.set(false);
    }

}