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

/**
 * This class encapsulates all the commands necessary
 * to run the tele-op shooting sequence.
 * 
 * This sequence is:
 * 1. Reverse the balls in storage slightly.
 * 2. Open the ball gate to allow balls to pass into the shooter.
 * 3. Align the turret.
 * 4. Rev up the shooter.
 * 5. Align the turret again (in case we were bumped while revving)
 * 6. Run intake forward to feed 1 ball into shooter.
 * 7. Repeat 3-6 four more times.
 * 
 * This sequence can be cancelled/restarted at any time by releasing
 * the firing button.
 */
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
}