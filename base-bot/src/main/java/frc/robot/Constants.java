// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder.BackendKind;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    

    // Remember objects and Abstraction. We seperate our code as much as possible within reason without increasing complexity. 

    /* We will define our Motors and possibly other finals that are related to drivetrain in this class. Name things that make sense to anyone that looks at the code
    /* or leave a comment so others understand what you are doing !
    */
    public static final class Drivetrain{

        // Remeber these numbers we are assigning are where we will position them on the can bus. 
                                                          // Ignore this is for an Example
        public static final int FRONT_LEFT_MOTOR = 1; public static final WPI_TalonFX fLM = new WPI_TalonFX(FRONT_LEFT_MOTOR); // Yes! you can do this you could write infite amount of code in one line
        public static final int BACK_LEFT_MOTOR = 2; public static final WPI_TalonFX bRM = new WPI_TalonFX(BACK_LEFT_MOTOR);  // However it can get very confusing fast. 
        public static final int FRONT_RIGHT_MOTOR = 3;
        public static final int BACK_RIGHT_MOTOR = 4;
        


    }


    public static final class Pnuematics {
        public static final int COMP = 10;
        public static final int D_SOLENOID_CHANNEL_0 = 0;
        public static final int D_SOLENOID_CHANNEL_1 = 1;


    }


    public static final class Shooter {
        public static final int SHOOTER_MOTOR_1 = 5;

        // Ball Control
        public static final boolean shooterBallIn = true;
        public static final boolean shooterBallOut = false;
    }


    public static final class IO {

        // Normally in computer science you start at 0, not 1. Hence why Joystick is at 0, as that is the position it is in on the Driverstation.
        // Driverstation slots under USB goes as follows: 0,1,2,3.
        public static final int JOYSTICK = 0;
        public static final int BLUE_BUTTONS = 1;
        public static final int CONTROLLER = 2;

    }
}
