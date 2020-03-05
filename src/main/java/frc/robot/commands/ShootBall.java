package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Limelight;

public class ShootBall extends SequentialCommandGroup {

    public ShootBall(Shooter shooter, Intake intake, Turret turret, Limelight limelight){
        addCommands(
            new AlignTurret(turret, limelight),
            new ShooterRev(shooter, limelight)/*,
            new TimedIntake(intake, -1.0, 100, IntakeMode.INTERNAL),
            new InstantCommand(() -> shooter.openBallGate()),
            new WaitCommand(0.1),
            new TimedIntake(intake, 1.0, 1000, IntakeMode.INTERNAL),
            new InstantCommand(() -> shooter.closeBallGate())*/
        );
    }
}