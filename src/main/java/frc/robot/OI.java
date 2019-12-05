/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);
  // driver joystick and buttons
  Joystick driverStick = new Joystick(0);

  // operator board buttons
  Joystick operatorBoard = new Joystick(1);
  JoystickButton cubeIn = new JoystickButton(operatorBoard, RobotMap.cubeInButton);
  JoystickButton cubeOut = new JoystickButton(operatorBoard, RobotMap.cubeOutButton);
  JoystickButton elevatorUp = new JoystickButton(operatorBoard, RobotMap.elevatorUpButtonNumber);
  JoystickButton elevatorDown = new JoystickButton(operatorBoard, RobotMap.elevatorDownButtonNumber);
  JoystickButton inclineUp = new JoystickButton(operatorBoard, RobotMap.angleUp);
  JoystickButton inclineDown = new JoystickButton(operatorBoard, RobotMap.angleDown);
  // Creating the joystick
  public Joystick getDriverStick() {
    return driverStick;
  }

  // Creating the Operator Board
  public Joystick getOperatorBoard() {
    return operatorBoard;
  }
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  public OI() {
    cubeIn.whileHeld(new IntakeIn());
    cubeOut.whileHeld(new IntakeOut());
    elevatorUp.whileHeld(new ElevatorUp());
    elevatorDown.whileHeld(new ElevatorDown());
    inclineUp.whileHeld(new InclineUp());
    inclineDown.whileHeld(new InclineDown());
  }
  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());
  
  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
