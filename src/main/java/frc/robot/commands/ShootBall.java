package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.TimedIntake.IntakeMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Limelight;

public class ShootBall extends SequentialCommandGroup {

    private final Shooter shooter;
    private final Turret turret;
    private final Intake intake;

    public ShootBall(Shooter shooter, Intake intake, Turret turret, Limelight limelight){
        this.shooter = shooter;
        this.turret = turret;
        this.intake = intake;
        
        addCommands(
            new AlignTurret(turret, limelight),
            new ShooterRev(shooter, limelight),
            new TimedIntake(intake, -1.0, 100, IntakeMode.INTERNAL),
            new InstantCommand(() -> shooter.openBallGate()),
            new WaitCommand(0.1),
            new TimedIntake(intake, 1.0, 1000, IntakeMode.INTERNAL),
            new InstantCommand(() -> shooter.closeBallGate())
        );
    }

    @Override
    public void end(boolean interrupted){
        shooter.setFlywheelPercent(0);
        turret.setRotatorSpeed(0);
        intake.setInternalRoller(0);
    }
}