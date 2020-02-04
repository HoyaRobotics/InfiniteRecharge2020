package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Gearbox;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the robot subsystems and their various commands
 * of the robot are declared.
 */
public class RobotContainer {

  private final Joystick controller = new Joystick(Constants.CONTROLLER);

  private final DriveBase driveBase = new DriveBase();
  private final Gearbox gearbox = new Gearbox();

  public RobotContainer() {
    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> controller.getY(Hand.kLeft), () -> controller.getX(Hand.kLeft)));

    new POVButton(controller, 0).whenPressed(() -> gearbox.setHighGear(true));
    new POVButton(controller, 180).whenPressed(() -> gearbox.setHighGear(false));
  }


  /**
   * Creates the autonomous command for the robot.
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
