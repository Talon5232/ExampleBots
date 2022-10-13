// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSub extends SubsystemBase {
  
  private final TalonFX shooterMotor1 = new TalonFX(Constants.Shooter.SHOOTER_MOTOR_1);

  /** Creates a new ShooterSub. */
  public ShooterSub() {}


  /**
   * 
   * @param setRotationSpeed Should be how fast you want to spin, thus 0-1
   * @param setDirection Should be 1 or -1, depending on which direction you want the motor to spin.
   * Think of it as Speed*Direction = 1 or -1 thus forward or reverse.
   */
  public void shooterControl(double setRotationSpeed, int setDirection){

    shooterMotor1.set(ControlMode.PercentOutput, setRotationSpeed*setDirection);

  }

  public void ballOut(){
    shooterControl(1, -1);
  }

  public void ballIn(){
    shooterControl(1, 1); 
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
