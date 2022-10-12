// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.DrivetrainSub;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final Joystick bigStick = new Joystick(Constants.IO.JOYSTICK);

  // The robot's subsystems and commands are defined here...   Technically this creates a new object and is slightly slower than getting an instance, which again is something we may tackle later in the year
  private final DrivetrainSub drivetrain = new DrivetrainSub();

  //private final DefaultDrive m_autoCommand = new DefaultDrive(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
    //This sets the default command for drive to the joystick and the () -> is putting y and x into your defaultDrive command, which executes your arcade drive Defined in DrivetrainSub
    // So you can see how your subsystem, command, and RobotContainer all work at varying levels of obscurity, and how you can view the robot in seperate objects.
    // Robot -> Manipulators -> RoboRio -> Main.java -> RobotContainer/Robot.java -> Subsystems/Commands
    drivetrain.setDefaultCommand(
      new DefaultDrive(
        drivetrain,
         () -> bigStick.getY(),
         () -> bigStick.getX())
    );

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
