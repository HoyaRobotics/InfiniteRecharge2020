package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.util.Logger;

/**
 * This class contains the methods that run during operation of the robot.
 */
public class Robot extends TimedRobot {
  private Command autonomousCommand;

  private RobotContainer robotContainer;

  /**
   * Called on robot startup.
   */
  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();

    Logger.info("Robot started");
  }

  /**
   * Called periodically. (after mode-specific periodic methods)
   */
  @Override
  public void robotPeriodic() {
    long start = System.currentTimeMillis();
    CommandScheduler.getInstance().run();

    Logger.flush();
    long loopTime = System.currentTimeMillis() - start;
    if(loopTime > 20)
      Logger.warn("Loop overrun: " + loopTime + "ms");

    SmartDashboard.putNumber("loopTime", loopTime);
  }

  /**
   * Called when robot enters disabled mode.
   */
  @Override
  public void disabledInit() {
    Logger.info("Entering disabled");
  }

  /**
   * Called periodically during disabled mode.
   */
  @Override
  public void disabledPeriodic() {
  }

  /**
   * Called when robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    // Fetch autonomous command from RobotContainer
    autonomousCommand = robotContainer.getAutonomousCommand();

    // Schedule the autonomous command
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }

    Logger.info("Entering autonomous");
  }

  /**
   * Called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * Called when robot enters teleop mode.
   */
  @Override
  public void teleopInit() {
    // Ensure autonomous stops running when beginning teleop
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }

    Logger.info("Entering teleop");
  }

  /**
   * Called periodically during teleop mode.
   */
  @Override
  public void teleopPeriodic() {
  }

  /**
   * Called when robot enters test mode.
   */
  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * Called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
