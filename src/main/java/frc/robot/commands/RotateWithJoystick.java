package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.subsystems.Turret;
import frc.robot.util.Utils;

public class RotateWithJoystick extends CommandBase {

    private final Turret turret;
    private final DoubleSupplier input;

    public RotateWithJoystick(Turret turret, DoubleSupplier input){
        this.turret = turret;
        this.input = input;

        addRequirements(turret);
    }

    @Override
    public void execute(){
        double speed = Utils.applyDeadband(input.getAsDouble(), Constants.CONTROL_DEADBAND) / 2;
        turret.setRotatorSpeed(speed);
        SmartDashboard.putNumber("turretSpeed", speed);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        turret.setRotatorSpeed(0);
    }

}