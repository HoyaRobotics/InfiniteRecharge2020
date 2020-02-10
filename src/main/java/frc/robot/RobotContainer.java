package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Gearbox;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the robot subsystems and their various commands
 * of the robot are declared.
 */
public class RobotContainer {

  private final Joystick controller = new Joystick(Constants.CONTROLLER);

  private final DriveBase driveBase = new DriveBase();
  private final Gearbox gearbox = new Gearbox();
  
  public RobotContainer() {
    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> controller.getY(DRIVE), () -> controller.getX(DRIVE)));

    JoystickButton toggleGear = new JoystickButton(controller, TOGGLE_GEAR);
    toggleGear.whenPressed(new InstantCommand(() -> gearbox.toggleGear()));
  }


  /**
   * Creates the autonomous command for the robot.
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
