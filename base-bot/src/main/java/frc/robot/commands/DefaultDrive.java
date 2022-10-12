// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSub;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DefaultDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  // Here you are creating "object" of the drive subsystem aka giving m_subsystem able to do things declared inside drivetrainSub
  private final DrivetrainSub m_subsystem;
  // m_foward and rotation are variables that we are declaring and not declaring a set speed, this will be done with your joystick in Robotconatiner 
  private final DoubleSupplier m_foward;
  private final DoubleSupplier m_rotation;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   * 
   * Some of this is repetive and in theory could be skipped via other structures, however this is easier to see what would and is happening. 
   */
  public DefaultDrive(DrivetrainSub subsystem, DoubleSupplier foward, DoubleSupplier rotation) {
    m_subsystem = subsystem;
    m_foward = foward;
    m_rotation = rotation;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Init, end, and isFinished can be deleted if you are not using, they are only left here so you can understand that they exist and can be used if you need.

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_subsystem.arcadeDrive(m_foward.getAsDouble(), m_rotation.getAsDouble());

  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
