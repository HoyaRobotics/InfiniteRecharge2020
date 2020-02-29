package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Logger;

import static frc.robot.Constants.*;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {

    private final Solenoid ballGate = new Solenoid(BALL_GATE);

    private final CANSparkMax left = new CANSparkMax(SHOOTER_LEFT, MotorType.kBrushless);
    private final CANSparkMax right = new CANSparkMax(SHOOTER_RIGHT, MotorType.kBrushless);

    private final CANPIDController pid = right.getPIDController();
    private final CANEncoder encoder = right.getEncoder();

    private double[] rpmLog = new double[20];
    private int rpmLogCounter = 0;

    private boolean gateOpen;

    public Shooter(){
        left.restoreFactoryDefaults();
        right.restoreFactoryDefaults();

        right.setOpenLoopRampRate(1);
        right.setClosedLoopRampRate(1);

        pid.setFeedbackDevice(encoder);
        pid.setFF(0.00019);
        pid.setP(0.0003);
        
        left.follow(right, true);

        closeBallGate();
    }

    @Override
    public void periodic(){
        rpmLog[rpmLogCounter] = getFlywheelRPM();

        if(rpmLogCounter < rpmLog.length - 1)
            rpmLogCounter++;
        else
            rpmLogCounter = 0;

        SmartDashboard.putNumber("flywheelRPM", encoder.getVelocity());
    }

    public boolean isStable(){
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;

        double sum = 0;
        double nonZero = 0;
        for(int i = 0; i < rpmLog.length; i++){
            double thisRPM = rpmLog[i];
            if(thisRPM == 0)
                continue;

            nonZero++;
            if(thisRPM < lowest)
                lowest = thisRPM;
            if(thisRPM > highest)
                highest = thisRPM;
            sum += thisRPM;
        }

        if(nonZero < 10)
            return false;

        double average = sum / nonZero;

        return (highest - average) < RPM_STABILITY_ERROR &&
                (average - lowest) < RPM_STABILITY_ERROR;
    }

    public void setFlywheelRPM(double rpm){
        pid.setReference(rpm, ControlType.kVelocity);
    }

    public void setFlywheelPercent(double percent){
        right.set(percent);
    }

    public double getFlywheelRPM(){
        return encoder.getVelocity();
    }

    public void toggleBallGate(){
        if(gateOpen){
            closeBallGate();
            Logger.info("Ball gate opened");
        }else{
            openBallGate();
            Logger.info("Ball gate closed");
            
        }
    }

    public void openBallGate(){
        ballGate.set(true);
        gateOpen = true;
    }

    public void closeBallGate(){
        ballGate.set(false);
        gateOpen = false;
    }

}