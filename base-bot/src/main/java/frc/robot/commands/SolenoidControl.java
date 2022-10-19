// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PnuematicSub;

public class SolenoidControl extends CommandBase {
  PnuematicSub m_solenoid;
  boolean control; 
  /** Creates a new SolenoidControl. */
  public SolenoidControl(PnuematicSub sub, boolean c) {
    m_solenoid = sub;
    control = c;

    addRequirements(m_solenoid);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // Solenoids are open or close, thus we can just switch.
    m_solenoid.solControl(control);
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
