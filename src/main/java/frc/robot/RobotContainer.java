package frc.robot;

import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ShootBall;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Gearbox;
import frc.robot.subsystems.Intake;
import frc.robot.util.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the robot subsystems and their various commands
 * of the robot are declared.
 */
public class RobotContainer {

  private final XboxController controller = new XboxController(CONTROLLER);
  private final Limelight limelight = new Limelight();

  private final DriveBase driveBase = new DriveBase();
  private final Gearbox gearbox = new Gearbox();
  //private final Intake intake = new Intake();
  
  public RobotContainer() {
    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> controller.getY(Controls.DRIVE), () -> controller.getX(Controls.DRIVE)));

    JoystickButton toggleGear = new JoystickButton(controller, Controls.TOGGLE_GEAR);
    toggleGear.whenPressed(new InstantCommand(() -> gearbox.toggleGear()));

    /*JoystickButton toggleIntakeRaised = new JoystickButton(controller, Controls.TOGGLE_INTAKE_RAISED);
    toggleIntakeRaised.whenPressed(new InstantCommand(() -> intake.toggleRaised()));

    JoystickButton runIntake = new JoystickButton(controller, Controls.RUN_INTAKE);
    runIntake.whenPressed(new InstantCommand(() -> intake.setPolycordRoller(INTAKE_SPEED)));
    runIntake.whenReleased(new InstantCommand(() -> intake.setPolycordRoller(0)));
    
    JoystickButton shootBall = new JoystickButton(controller, Controls.SHOOT_BALL);
    shootBall.whileHeld(new ShootBall(shooter, intake, turret, limelight));
    */
  }


  /**
   * Creates the autonomous command for the robot.
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
