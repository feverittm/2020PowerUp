/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
	public static class Ports {
		public static int
		// CAN, don't use address 0! It is a magic broadcast address
		elevatorVictorPort = 1,
		elevatorTalonPort = 2,
		leftTalonPort = 3,
		leftVictorPort = 4,
		leftVictorPort2 = 5,
		rightVictorPort = 6,
		rightVictorPort2 = 7,
		rightTalonPort = 8,
		
		//ANALOGINPUT
		leftCollectorSensorInput = 0,
		rightCollectorSensorInput = 1,
		
		//PWM
		climberVictorPort = 0,
		climberVictorPort2 = 1,
		leftCollectorPort = 2,
		rightCollectorPort = 3,
		
		//PNEUMATICS
		elevatorSolenoidPort = 0,
		elevatorSolenoidPort2 = 1,
		
		//GAMEPADS
		GamePad1 = 0, 
		GamePad2 = 1;
		
		//USB
		public static final SerialPort.Port AHRS = SerialPort.Port.kUSB;
		
	}
	
	public static class PDPPorts {
		public static int
		
		rightDriveTrain = 0,
		rightDriveTrain2 = 1,
		climber = 2,
		climber2 = 3,
		intake = 6,
		intake2 = 7,
		elevatorTalon = 10,
		elevatorVictor = 11,
		leftDriveTrain = 12,
		leftDriveTrain2 = 13,
		leftDriveTrainTalon = 14,
		rightDriveTrainTalon = 15;
	}
	
	public static class Values {
		public static int
		ticksPerRev = 4096; // CTRE Mag Encoder

		public static double
		inchesPerTick = (3.954*Math.PI)/4096,	//inches per encoder tick
		ticksPerFoot = ((49152/(3.97*Math.PI)))*0.9, //3940, //encoder ticks per foot	
		
		// Robot Attributes (in inches)
		robotLength = 33.25,
		robotWidth = 37.25,
		robotWheelBase = 30,
		robotWheelDia = 3.9,
				
		autoSwitchDistance = 14, //in feet (supposedly)
		autoScaleDistance = 28, //in feet
		
		//From back of wall to front of switch. Measure these at each comp!
		autoLeftSwitchTotal = 139/12, //11.79167, 
		autoRightSwitchTotal = 139/12, //11.77083,
		autoCenterSwitchTotal = 139/12, //12,
		
		elevatorTopHeight = 36853, //<--CB top height
							//22336, <--PB top height
		elevatorHighMidHeight = 16000,
		elevatorLowMidHeight = 6135,
		elevatorSwitchHeight = 14333,
								//14083 <--PB switch height,
		elevatorBottomHeight = 0,
		elevatorSafeDriveHeight = 2750, //good for PB and CB
		
		slowcollectspeed = 0.25,
		fastcollectspeed = 0.75,
		collectspeed = 1,
		
		driveDistanceP = 0.00025,
		driveDistanceI = 0.0,
		driveDistanceD = 0.0,
		elevatorPidP = 0.75,
		elevatorPidI = 0.001,
		elevatorPidD = 0, //0.1,
		climbspeed = 1.0,
		
		driveAngleP = 0.015, //0.035, then 0.0138, then 0.0244 (important one), then 0.00694, then 0.0138
		driveAngleI = 0.002595, //0.000138 (important one) then 0.000038 then 0.000138 then 0.0001037 
		//then 0.000138 then 0.000514
		driveAngleD = 0,
		
		//Current limits in amps
		drivetrainLeftLimit = 81, //81
		drivetrainRightLimit = 81, //81
		collectorLeftLimit = 12,
		collectorRightLimit = 12,
		elevatorLimit = 55,
		climberLimit = 50,

		// design pattern to make all doubles above end with a comma and not have to worry about...
		// "Do I end with a comma or a semicolon?"
		last=0.0;
				
	}

	public static class Buttons {
		public static int
		
		//ELEVATOR CONTROLS
		elevatorManualUp = 6, //Right Bumper, GamePad1
		elevatorManualDown = 5, //Left Bumper, GamePad1
		
		bottomPosition 	= 1, 	//A, GamePad1
		highMidPosition = 2, 	//B, GamePad1
		lowMidPosition 	= 3, 	//X, Gamepad1
		topPosition 	= 4,	//Y, GamePad1
		switchPosition 	= 8, 	//Start, GamePad1
				
		//COLLECTOR CONTROLS
		collectButton 	= 4, 	//Right Trigger, Gamepad2
		uncollectButton = 1, 	//Left Trigger, Gamepad2
		flopButton 		= 7, 	//Back, Gamepad1
		
		//CLIMBER CONTROLS
		climbbutton 	= 2,	 //Circle, Gamepad2
		unclimbbutton 	= 3; 	//X, Gamepad2
	}
}
