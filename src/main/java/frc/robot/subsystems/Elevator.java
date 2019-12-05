/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX elevatorMaster;
  private WPI_TalonSRX elevatorSlave;
  private boolean atBot = false;

  double elevatorIncrease;


  /**
   * Initialize Elevator
   * 
   * @param profile - PID profile
   */
  public Elevator(WPI_TalonSRX masterMotor, WPI_TalonSRX slaveMotor) {
    elevatorMaster = masterMotor;
    elevatorSlave = slaveMotor;
    defaultPIDLSMotor();
    elevatorSlave.follow(elevatorMaster);
    elevatorMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 20);
    elevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 20);

  }

  public void currentLimit() {
    elevatorMaster.configContinuousCurrentLimit(35, 0);
    elevatorMaster.configPeakCurrentLimit(39, 0);
    elevatorMaster.configPeakCurrentDuration(150, 0);
    elevatorMaster.enableCurrentLimit(true);
  }

  
  //
  /**
   * Default PID profile (0)
   */
  public void defaultPIDLSMotor() {
    elevatorMaster.setSensorPhase(true);
    elevatorMaster.selectProfileSlot(RobotMap.defaultPID, 0);
    elevatorMaster.config_kF(0, RobotMap.PIDF, 20);
    elevatorMaster.config_kP(0, RobotMap.PIDP, 20);
    elevatorMaster.config_kI(0, RobotMap.PIDI, 20);
    elevatorMaster.config_kD(0, RobotMap.PIDD, 20);
    elevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 20);
  }

  /**
   * Configure PID profile
   * 
   * @param profile - profile (0 or 1)
   * @param fVal    - f constant
   * @param pVal    - position constant
   * @param iVal    - integral constant
   * @param dVal    - derivative constant
   *
   *                Calculates derivative to maximize efficiency in movement.
   */
  public void configPIDProfile(int profile, double fVal, double pVal, double iVal, double dVal) {
    elevatorMaster.setSensorPhase(true);
    elevatorMaster.selectProfileSlot(profile, 0);
    elevatorMaster.config_kF(0, fVal, 20);
    elevatorMaster.config_kP(0, pVal, 20);
    elevatorMaster.config_kI(0, iVal, 20);
    elevatorMaster.config_kD(0, dVal, 20);
    elevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, profile, 20);
  }

  /**
   * Get encoder value for the elevator
   * 
   * @return - encoder value for the elevator
   */
  public int getEncoder() {
    return elevatorMaster.getSensorCollection().getQuadraturePosition();
  }

  public void resetEncoder() {
    elevatorMaster.getSensorCollection().setQuadraturePosition(0, 20);
  }


  public void setElevatorSpeed(double speed){
    elevatorMaster.set(speed);
  }
  // public void usingButtons(Joystick buttons) {
  //   if (buttons.getRawButton(RobotMap.elevatorUpButtonNumber)) {
  //     elevator.set(0.6);
  //   } else if (buttons.getRawButton(RobotMap.elevatorDownButtonNumber)) {
  //     elevator.set(-0.3);
  //   } else {
  //     elevator.set(0);
  //   }
  // }

  /**
   * Convert Feet to encoder distance and move
   * 
   * @param pos - Movement by feet
   */
  public void setPositionFeet(double feet) {
    int feetT = (int) (RobotMap.ticksPerFoot * feet);
    System.out.println("Moving to tick: " + feetT);
    setPosition(feetT);
  }

  /**
   * Move elevator to position in ticks
   */
  public void setPosition(double tick) {
    elevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 20);
    if (tick > RobotMap.maxEncoderValue) {
      elevatorMaster.set(ControlMode.Position, RobotMap.maxEncoderValue);
    } else {
      elevatorMaster.set(ControlMode.Position, tick);
    }
  }

  /**
   * Convert inches to encoder and move
   * 
   * @param pos - movement in inches
   */
  public void setPosInches(int inch) {
    int inchT = RobotMap.ticksPerInch * inch;
    setPosition(inchT);
  }

  public double getCurrent(WPI_TalonSRX motor) {
    return motor.getOutputCurrent();
  }

  public double getTotalCurrent() {
    return getCurrent(elevatorMaster) + getCurrent(elevatorSlave);
  }

  public String getElevatorCurrent() {
    return "Elevator Current: " + (elevatorMaster.getOutputCurrent() + elevatorSlave.getOutputCurrent());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
