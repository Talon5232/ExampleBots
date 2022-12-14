// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PnuematicSub extends SubsystemBase {

  Compressor compressor = new Compressor(Constants.Pnuematics.COMP, PneumaticsModuleType.REVPH); 

  private final DoubleSolenoid m_Solenoid = 
    new DoubleSolenoid(Constants.Pnuematics.COMP, PneumaticsModuleType.REVPH, Constants.Pnuematics.D_SOLENOID_CHANNEL_0, Constants.Pnuematics.D_SOLENOID_CHANNEL_1);


  /** Creates a new PnuematicSub. */
  public PnuematicSub() {}

  //default on
  public void CompressorEnable(){
    // We just need this to happen on Robot start, it will keep compressor between 90 and 120 psi
    // If this seems to not work, you could include it in Periodic, or put it inside the SolenoidControl command !
    compressor.enableAnalog(90.0, 120.0);

  }

  public void solControl(boolean control){

    // Forward is open, Reverse is Closed. 
    if(control == true){
      m_Solenoid.set(Value.kForward);
    }
    else {
      m_Solenoid.set(Value.kReverse);
    }


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
