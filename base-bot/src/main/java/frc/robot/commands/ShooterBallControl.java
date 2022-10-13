// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSub;

public class BallOut extends CommandBase {
  private final ShooterSub m_shooter;
  private final int m_speed;
  private final int m_direction;

  /** Creates a new BallOut. */
  public BallOut(ShooterSub sub, int speed, int direction) {
    m_shooter = sub;
    m_speed = speed;
    m_direction = direction;

    addRequirements(sub);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public boolean setDirectionOut(boolean x) {

    return this.direction = x;

  }


  public boolean setDirectionIn(boolean x) {
    return this.direction = x;
  }

  public boolean getDirection(){
    return direction;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_shooter.shooterControl(m_speed, m_direction);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }


private boolean direction;


}
