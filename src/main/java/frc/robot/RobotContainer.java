package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.AlignTurret;
import frc.robot.commands.ControlClimber;
import frc.robot.commands.DriveForTime;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ShooterRev;
import frc.robot.commands.TimedIntake;
import frc.robot.commands.TurnTurretForTime;
import frc.robot.commands.TimedIntake.IntakeMode;
import frc.robot.commands.ShootBall;
import frc.robot.commands.RotateWithJoystick;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Gearbox;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * This class is where the robot subsystems and their various commands
 * of the robot are declared.
 */
public class RobotContainer {

  private final XboxController driver = new XboxController(DRIVER);
  private final XboxController operator = new XboxController(OPERATOR);
  private final Limelight limelight = new Limelight();

  private final DriveBase driveBase = new DriveBase();
  private final Gearbox gearbox = new Gearbox();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter(driver);
  private final Turret turret = new Turret();
  private final Climber climber = new Climber();
  
  public RobotContainer() {
    //Driver controls:
    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> driver.getY(Controls.DRIVE), () -> driver.getX(Controls.DRIVE)));
    climber.setDefaultCommand(new ControlClimber(climber, () -> driver.getPOV()));

    JoystickButton toggleGear = new JoystickButton(driver, Controls.TOGGLE_GEAR);
    toggleGear.whenPressed(new InstantCommand(() -> gearbox.toggleGear()));

    final double INTERNAL_SPEED = 1.0;
    final double EXTERNAL_SPEED = 1.0;

    JoystickButton runIntakeFwd = new JoystickButton(driver, Button.kBumperRight.value);
    runIntakeFwd.whenPressed(new InstantCommand(() -> {
      intake.setInternalRoller(INTERNAL_SPEED);
      intake.setExternalRoller(-EXTERNAL_SPEED); 
    }
    ));
    runIntakeFwd.whenReleased(new InstantCommand(() -> {
      intake.setInternalRoller(0);
      intake.setExternalRoller(0);
    }
    ));

    JoystickButton runIntakeRvs = new JoystickButton(driver, Button.kBumperLeft.value);
    runIntakeRvs.whenPressed(new InstantCommand(() -> {
      intake.setInternalRoller(-INTERNAL_SPEED);
      intake.setExternalRoller(EXTERNAL_SPEED); 
    }
    ));
    runIntakeRvs.whenReleased(new InstantCommand(() -> {
      intake.setInternalRoller(0);
      intake.setExternalRoller(0); 
    }
    ));

    JoystickButton toggleIntakeRaised = new JoystickButton(driver, Controls.TOGGLE_INTAKE_RAISED);
    toggleIntakeRaised.whenPressed(new InstantCommand(() -> intake.toggleRaised()));
    
    JoystickButton toggleBallGate = new JoystickButton(driver, Controls.TOGGLE_BALL_GATE);
    toggleBallGate.whenPressed(new InstantCommand(() -> shooter.toggleBallGate()));

    JoystickButton shootBall = new JoystickButton(driver, Button.kA.value);
    ShootBall shootBallCmd = new ShootBall(shooter, intake, turret, limelight);
    shootBall.whenPressed(shootBallCmd);
    shootBall.whenReleased(new InstantCommand(() -> {
      shootBallCmd.cancel();
      shooter.setFlywheelPercent(0);
      shooter.closeBallGate();
      turret.setRotatorSpeed(0);
    }));

    JoystickButton manualSpinShooter = new JoystickButton(driver, 7);
    manualSpinShooter.whenPressed(new InstantCommand(() -> shooter.setFlywheelRPM(4000)));
    manualSpinShooter.whenReleased(new InstantCommand(() -> shooter.setFlywheelRPM(0)));

    //Operator controls:
    turret.setDefaultCommand(new RotateWithJoystick(turret, () -> operator.getX(Hand.kLeft)));

    JoystickButton toggleLimelightZoom = new JoystickButton(operator, Button.kB.value);
    toggleLimelightZoom.whenPressed(new InstantCommand(() -> limelight.toggleZoom()));

    JoystickButton incRPMOffset = new JoystickButton(operator, Button.kBumperRight.value);
    incRPMOffset.whenPressed(new InstantCommand(() -> shooter.incrementRPMOffset(100)));

    JoystickButton decRPMOffset = new JoystickButton(operator, Button.kBumperLeft.value);
    decRPMOffset.whenPressed(new InstantCommand(() -> shooter.decrementRPMOffset(100)));
  }

  
  /**
   * Creates the autonomous command for the robot.
   */
  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new InstantCommand(() -> shooter.openBallGate()),
      new ParallelCommandGroup(
        new TurnTurretForTime(turret, 0.23, 4),
        new DriveForTime(driveBase, 0.6, 4)
      ),
      new AlignTurret(turret, limelight),
      new ShooterRev(shooter, limelight),
      new TimedIntake(intake, 1.0, 0.22, IntakeMode.INTERNAL),
      new ShooterRev(shooter, limelight),
      new TimedIntake(intake, 1.0, 0.15, IntakeMode.INTERNAL),
      new ShooterRev(shooter, limelight),
      new TimedIntake(intake, 1.0, 0.15, IntakeMode.INTERNAL)
    );
  }
}
