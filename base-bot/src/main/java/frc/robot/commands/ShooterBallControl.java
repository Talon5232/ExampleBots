// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSub;

public class ShooterBallControl extends CommandBase {
  private final ShooterSub m_shooter;
  private final boolean m_direction;

  /** Creates a new BallOut. */
  public ShooterBallControl(ShooterSub sub, boolean direction) {
    m_shooter = sub;
    m_direction = direction;

    addRequirements(m_shooter);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //Controlling our execute like this allow us to use 1 command instead of 2 for controlling in/out movement. Keeps our code slightly more condensed. 
    if (m_direction == true){
      m_shooter.ballIn();
    }
    else if (m_direction == false){
      m_shooter.ballOut();
    }
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
