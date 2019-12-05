package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.*;

public class Drive extends Subsystem {
  // Declaring Drive Masters and Slaves
  private final WPI_TalonSRX leftDriveMaster;
  private final WPI_TalonSRX leftDriveSlave;
  private final WPI_TalonSRX rightDriveMaster;
  private final WPI_TalonSRX rightDriveSlave;

  public Drive(WPI_TalonSRX l1, WPI_TalonSRX l2, WPI_TalonSRX r1, WPI_TalonSRX r2) {
    leftDriveMaster = l1;
    leftDriveSlave = l2;

    leftDriveSlave.set(ControlMode.Follower, leftDriveMaster.getDeviceID());

    rightDriveMaster = r1;
    rightDriveSlave = r2;

    rightDriveSlave.set(ControlMode.Follower, rightDriveMaster.getDeviceID());
  };

  /**
   * Sets speed for the left side
   * 
   * @param speed
   */
  public void setLeftSpeed(double speed) {
    leftDriveMaster.set(-speed);
  }

  /**
   * Sets speed for right side
   * 
   * @param speed
   */
  public void setRightSpeed(double speed) {
    rightDriveMaster.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TankDrive());
  }
}