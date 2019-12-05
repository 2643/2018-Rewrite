/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  public static final WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(14); // Front Left 14
  public static final WPI_TalonSRX leftDrive2 = new WPI_TalonSRX(15); // Back left 15 No work
  public static final WPI_TalonSRX rightDrive1 = new WPI_TalonSRX(16); // Front Right 16
  public static final WPI_TalonSRX rightDrive2 = new WPI_TalonSRX(1); // Back Right 1 No work

  public static int elevatorDownButtonNumber = 5;
  public static int elevatorUpButtonNumber = 4;
  public static int cubeOutButton = 2;
  public static int cubeInButton = 3;
  public static int rightDriverAxis = 5;
  public static int leftDriverAxis = 1;
  public static int angleUp = 13;
  public static int angleDown = 14;

  // Intake motors
  public static final WPI_TalonSRX leftIntake = new WPI_TalonSRX(9);
  public static final WPI_TalonSRX rightIntake = new WPI_TalonSRX(6);

  public static final WPI_TalonSRX inclineMotor = new WPI_TalonSRX(4);

  // Elevator Motors
  public static final WPI_TalonSRX elevator1 = new WPI_TalonSRX(5);
  public static final WPI_TalonSRX elevator2 = new WPI_TalonSRX(30);

  // Elevator variables
  public static int ticksPerFoot = 3530;
  public static int ticksPerInch = ticksPerFoot / 12;
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static final int defaultPID = 0;
  public static final double PIDF = 0.5;
  public static final double PIDP = 0.5;
  public static final double PIDI = 0;
  public static final double PIDD = 0;

  public static final int maxEncoderValue = 60000; // TODO //From 12750
}
