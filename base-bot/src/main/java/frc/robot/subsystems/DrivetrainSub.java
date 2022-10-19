// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.concurrent.CopyOnWriteArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import org.ejml.dense.row.decomposition.eig.watched.WatchedDoubleStepQREigen_DDRM;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSub extends SubsystemBase {
  /*
  * Defining our motors for the drivetrain, in the future you may also define items such as encoders and other data systems here too. 
  * WPI_TalonFX is the motor controller you will be using for the game, however during the build season you will almost exclusively use WPI_TalonFX (Falon500s). 
  * Constans.Drivetrain is calling up the classes and underlying variables you defined in the Constants class.
  * Constants might seem like a lot of work however the benefits are worth it.
  * The benefit is that you are able to change all the locations of any motor inside one class, meaning you dont have to move between many classes looking for one motor. 
  * Not to mention it is most autocomplete with the first letter or two and hitting tab. 

  * Private final means that only this class can access the variables, and it tells everything else that the location of this motor cannont and will not change. Without creating a new object.
  */

  // Remember objects and Abstraction. We seperate our code as much as possible within reason without increasing complexity. 
  /*
  * We will tackle a few ways of declaring our drivetrain. It will be up to you guys how you choose to ultimately do it, I'm just showing some possibilities. 
  */
  // Downside to this first is a few more lines of code, and a lot more code within each class.
  private final WPI_TalonFX frontLeftMotor = new WPI_TalonFX(Constants.Drivetrain.FRONT_LEFT_MOTOR);
  private final WPI_TalonFX backLeftMotor = new WPI_TalonFX(Constants.Drivetrain.BACK_LEFT_MOTOR);
  private final WPI_TalonFX frontRightMotor = new WPI_TalonFX(Constants.Drivetrain.FRONT_RIGHT_MOTOR);
  private final WPI_TalonFX backRightMotor = new WPI_TalonFX(Constants.Drivetrain.BACK_RIGHT_MOTOR);

  // This is grouping the defined above motors into groups left side and right side. 
  private final MotorControllerGroup leftControllerGroup = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
  private final MotorControllerGroup rightControllerGroup = new MotorControllerGroup(frontRightMotor, backRightMotor);

  // Here we are assinging motors to the diffDrive. What this is ultimately doing is grouping those motors in again and negating half the inputs. 
  // AKA instead of applying speeds and directions to 4 motors, and in swerve 8! You will only have to do apply it to 1 and most of the rest is done automatically by DifferentialDrive.
  private final DifferentialDrive differentialDrive = new DifferentialDrive(leftControllerGroup, rightControllerGroup); // Tip: Hovering over DifferntialDrive will explain even more. 

  // Second way to do what we did above
  // This way appears to be shorter however in the future when getting sensor positions you will have to declare the motors similar to lines 34-37.
  private final MotorControllerGroup lControllerGroup = new MotorControllerGroup(
    new WPI_TalonFX(Constants.Drivetrain.FRONT_LEFT_MOTOR),
    new WPI_TalonFX(Constants.Drivetrain.BACK_LEFT_MOTOR));

  private final MotorControllerGroup rControllerGroup = new MotorControllerGroup(
    new WPI_TalonFX(Constants.Drivetrain.FRONT_RIGHT_MOTOR),
    new WPI_TalonFX(Constants.Drivetrain.BACK_RIGHT_MOTOR));

  private final DifferentialDrive dDrive = new DifferentialDrive(lControllerGroup, rightControllerGroup);


  // Another way to do this is to declare your motors inside another class such as Constants and thus would have something as below (I will not do the entire example for this.)
  // You should be able to imply how to do RightGroup and Differntial drive from above code. 
  // Downside to this is that if you do it inside constants, then constants isnt really just constants any more and can become very crowded. Thus a new class would be reccomended
  private final MotorControllerGroup leftMotorControllerGroup2 = new MotorControllerGroup(Constants.Drivetrain.fLM, Constants.Drivetrain.bRM);


  /** Creates a new ExampleSubsystem. */
  public DrivetrainSub( ) {  }


  /*
  @param fow, foward double from input of joystick
  @param rot, rot double from input of joystick
  This is how the drivetrain command and others will actually access our drivetrain defined above. 
  */
  public void arcadeDrive(double fow, double rot){
    dDrive.arcadeDrive(fow, rot);
  }

  // You can use this to scale how fast you drive.
  public void setOutput(double max){
    dDrive.setMaxOutput(max);
  }

  // Other stuff that we will include in this encoders, gyro, and other data we may need, or need to share to other classes. 


  // This runs about every 20ms from start, meaning anything in here will essentially happen every 20ms
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  // FRC now has a way to build you robot and drive it via sim, this is something that we may experiment with if we have time before season start. 
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
