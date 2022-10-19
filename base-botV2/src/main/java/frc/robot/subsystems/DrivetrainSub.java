// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;

import org.ejml.dense.row.decomposition.eig.watched.WatchedDoubleStepQREigen_DDRM;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  // Our gyro, may upgrade to a pigeon 2.0 in the future
  WPI_PigeonIMU pigeon = new WPI_PigeonIMU(Constants.Data.GYRO);




  /** Creates a new DrivetrainSub. */
  public DrivetrainSub( ) {  


    // Always good practice to set a neutral mode or "not in use" mode for any object of the robot
    frontLeftMotor.setNeutralMode(NeutralMode.Brake);
    backLeftMotor.setNeutralMode(NeutralMode.Brake);
    frontRightMotor.setNeutralMode(NeutralMode.Brake);
    backRightMotor.setNeutralMode(NeutralMode.Brake);
    // Highly reccomend looking into implementing configFactoryDefault, enableVoltageCompensation, configVoltageCompSaturation especially if you are having battery problems

    // Clears any faults from previous uses.
    pigeon.configFactoryDefault();


    

  }

  // We will put things in here that we want displayed on our Smartdashboard, data, cameras, etc. Id suggest adding a second method, meant for debuging only. 
  // Debug mode could include things such as CAN Bus id numbers, voltages, Raw Encoder or gyro data, etc. 
  // For sake of learning this will include a little bit of both worlds
  public void putDataToShuffleboard(){
    SmartDashboard.putNumber("y", getGyro()); // Always good to use methods that you have used. 
    SmartDashboard.putNumber("Can ID", pigeon.getDeviceID());

    SmartDashboard.putNumber("Left Side Encoders", getLeftSideEncoders());
    SmartDashboard.putNumber("Right Side Encoders", getRightSideEncoders());

  }

  /** 
  @param fow, foward double from input of joystick
  @param rot, rot double from input of joystick
  This is how the drivetrain command and others will actually access our drivetrain defined above. 
  */
  public void arcadeDrive(double fow, double rot){
    differentialDrive.arcadeDrive(fow, rot);
    putDataToShuffleboard();
  }

  // You can use this to scale how fast you drive.
  public void setOutput(double max){
    differentialDrive.setMaxOutput(max);
  }

  // Other stuff that we will include in this encoders, gyro, and other data we may need, or need to share to other classes. 

  // Set and Get are a very important concept to grasp especially if you further your computer science work.

  //#region Gyro

  public double setGyro(){
    return this.g_Angle = pigeon.getYaw();
  }

  public double getGyro(){
    return g_Angle;
  }
   
  // As a saftey measure you should reset your gyro/encoders on robot init. 
  public void resetGyro(){
    pigeon.reset();
    pigeon.setYaw(0);
    setGyro();
  }

  //#endregion


  //#region Encoders
  // Realistically you only need front left front right, or back left back right, however sometimes redundancy is nice to have.
  public void setFrontLeftEncoder(){
    double setFrontLeftEncoders = frontLeftMotor.getSelectedSensorPosition(); // This will be in hundred of thousands, in your own practice you should filter this down.
    this.encoderFrontLeft = setFrontLeftEncoders;
  }

  public void setBackLeftEncoder(){
   double setBackLeftEncoder = backLeftMotor.getSelectedSensorPosition();
   this.encoderBackLeft = setBackLeftEncoder; 
  }

  public void setFrontRightEncoder(){
    double setFrontRightEncoders = frontRightMotor.getSelectedSensorPosition();
    this.encoderFrontRight = setFrontRightEncoders;
  }

  public void setBackRightEncoder(){
    double setBackRightEncoder = backRightMotor.getSelectedSensorPosition();
    this.encoderBackRight = setBackRightEncoder;
  }

  public double getLeftSideEncoders(){
    return ((encoderFrontLeft + encoderBackLeft) / 2);
  }
  
  public double getRightSideEncoders(){
    return ((encoderFrontRight + encoderBackLeft) / 2);
  }

  public void SetAllEncoders(){
    setBackLeftEncoder();
    setFrontLeftEncoder();
    setFrontRightEncoder();
    setBackLeftEncoder();
  }
/*
  public void GetAllEncoders(){
    getLeftSideEncoders();
    getRightSideEncoders();
  }
*/
  public void resetEncoders(){
    
    frontLeftMotor.setSelectedSensorPosition(0);
    backLeftMotor.setSelectedSensorPosition(0);
    frontRightMotor.setSelectedSensorPosition(0);
    backRightMotor.setSelectedSensorPosition(0);

    SetAllEncoders();

  }


  //#endregion













//#region 


  // This runs about every 20ms from start, meaning anything in here will essentially happen every 20ms
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setGyro();
    SetAllEncoders();
    putDataToShuffleboard();

  }


  // FRC now has a way to build you robot and drive it via sim, this is something that we may experiment with if we have time before season start. 
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  //#endregion


  private double g_Angle;
  private double encoderFrontLeft;
  private double encoderBackLeft;
  private double encoderFrontRight;
  private double encoderBackRight;
}


