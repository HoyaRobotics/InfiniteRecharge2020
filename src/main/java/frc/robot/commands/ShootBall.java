package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.TimedIntake.IntakeMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.util.Logger;
import frc.robot.subsystems.Limelight;

public class ShootBall extends SequentialCommandGroup {

    public ShootBall(Shooter shooter, Intake intake, Turret turret, Limelight limelight){
        addCommands(
            new TimedIntake(intake, -1.0, 0.1, IntakeMode.INTERNAL),
            new WaitCommand(0.1),
            new InstantCommand(() -> shooter.openBallGate()),
            new AlignTurret(turret, limelight),
            new ShooterRev(shooter, limelight),
            new AlignTurret(turret, limelight),
            new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL),
            new AlignTurret(turret, limelight),
            new ShooterRev(shooter, limelight),
            new AlignTurret(turret, limelight),
            new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL),
            new AlignTurret(turret, limelight),
            new ShooterRev(shooter, limelight),
            new AlignTurret(turret, limelight),
            new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL),
            new AlignTurret(turret, limelight),
            new ShooterRev(shooter, limelight),
            new AlignTurret(turret, limelight),
            new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL),
            new AlignTurret(turret, limelight),
            new ShooterRev(shooter, limelight),
            new AlignTurret(turret, limelight),
            new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL)
        );
    }

    @Override
    public void initialize(){
        Logger.info("Beginning shooting sequence");
    }

    @Override
    public void end(boolean interrupted){
        Logger.info("Finished shooting sequence");
    }
}