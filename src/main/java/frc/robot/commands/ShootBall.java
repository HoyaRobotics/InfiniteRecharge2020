package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.util.Limelight;

public class ShootBall extends SequentialCommandGroup {

    public ShootBall(Shooter shooter, Intake intake, Turret turret, Limelight limelight){
        addCommands(
            new ParallelCommandGroup(new AlignTurret(turret, limelight), new ShooterRev(shooter, limelight)),
            new ReleaseBall(intake, shooter)
        );
    }

    @Override
    public void end(boolean interrupted){
        shooter.setFlywheelRPM(0);
    }
}