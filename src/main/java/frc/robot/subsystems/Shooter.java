package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
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

    private final XboxController gateFeedback;

    private final Solenoid ballGate = new Solenoid(BALL_GATE);

    private final CANSparkMax left = new CANSparkMax(SHOOTER_LEFT, MotorType.kBrushless);
    private final CANSparkMax right = new CANSparkMax(SHOOTER_RIGHT, MotorType.kBrushless);

    private final CANPIDController pid = right.getPIDController();
    private final CANEncoder encoder = right.getEncoder();

    private double[] rpmLog = new double[20];
    private int rpmLogCounter = 0;

    private boolean gateOpen;

    private double targetRPM;
    private double rpmOffset;

    public Shooter(XboxController gateFeedback){
        this.gateFeedback = gateFeedback;
        
        left.restoreFactoryDefaults();
        right.restoreFactoryDefaults();

        right.setOpenLoopRampRate(1);
        right.setClosedLoopRampRate(1);

        pid.setFeedbackDevice(encoder);
        pid.setFF(SHOOTER_FF);
        pid.setP(SHOOTER_P);
        
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
        SmartDashboard.putNumber("rpmOffset", rpmOffset);
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
        targetRPM = rpm;
        pid.setReference(targetRPM + rpmOffset, ControlType.kVelocity);
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
        gateFeedback.setRumble(RumbleType.kLeftRumble, 0);
        gateFeedback.setRumble(RumbleType.kRightRumble, 0);
    }

    public void closeBallGate(){
        ballGate.set(false);
        gateOpen = false;
        gateFeedback.setRumble(RumbleType.kLeftRumble, 0.5);
        gateFeedback.setRumble(RumbleType.kRightRumble, 0.5);
    }

    public void incrementRPMOffset(double amount){
        rpmOffset += amount;
        setFlywheelRPM(targetRPM);
        Logger.info("Incremented RPM offset to " + rpmOffset);
    }

    public void decrementRPMOffset(double amount){
        rpmOffset -= amount;
        setFlywheelRPM(targetRPM);
        Logger.info("Decremented RPM offset to " + rpmOffset);
    }
}