package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
  private Main() {
  }

  /**
   * Main initialization function.
   */
  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
