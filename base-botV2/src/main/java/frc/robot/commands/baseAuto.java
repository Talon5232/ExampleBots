// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.Drivetrain;
import frc.robot.subsystems.DrivetrainSub;
import frc.robot.subsystems.ShooterSub;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class baseAuto extends SequentialCommandGroup {
  /** Creates a new baseAuto. */
  public baseAuto(DrivetrainSub m_drive, ShooterSub m_shooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
   
   
   // Incredibly simple auto, drives forwards for 1 second stops, then shoots for 1 second then stops. 
    addCommands(
    new SequentialCommandGroup(
      new DefaultDrive(m_drive, 1.0, 0.0).withTimeout(1),
      new ShooterBallControl(m_shooter, false).withTimeout(1)
    )



    );
  }
}
