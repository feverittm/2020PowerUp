package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Bugs: Follower motor not working. ((More than)Possibly not software related)
 */
public class Elevator extends SubsystemBase {

	private DoubleSolenoid elevatorSolenoid;
	private TalonSRX Motor;
	private VictorSPX follower;
	public SensorCollection sensorCollection;
	public static final double absoluteTolerance = 0.01;
	public boolean isZeroed = false;
	public int absolutePosition;
	public int delayCount = 0;
	Preferences setpointPrefs;

	public int index = 0;
	public double[] heightList = new double[] { Constants.Values.elevatorTopHeight,
			Constants.Values.elevatorHighMidHeight, Constants.Values.elevatorLowMidHeight,
			Constants.Values.elevatorBottomHeight, Constants.Values.elevatorSwitchHeight };

	public int flop; // whether the collector is "flopped" down or not
	public double elevatorCurrent;

	// Initialize your subsystem here
	public Elevator() {

		elevatorSolenoid = new DoubleSolenoid(Constants.Ports.elevatorSolenoidPort,
				Constants.Ports.elevatorSolenoidPort2);

		Motor = new TalonSRX(Constants.Ports.elevatorTalonPort);
		follower = new VictorSPX(Constants.Ports.elevatorVictorPort);

		follower.follow(Motor);

		follower.setInverted(false);

		absolutePosition = Motor.getSelectedSensorPosition(0); // & 0xFFF;
		Motor.setSelectedSensorPosition(absolutePosition, 0, 10);

		Motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		Motor.setSensorPhase(false);
		Motor.clearStickyFaults(10);
		Motor.configNominalOutputForward(0, 10);
		Motor.configNominalOutputReverse(0, 10);
		Motor.configPeakOutputForward(1.0, 10);
		Motor.configPeakOutputReverse(-0.35, 10);
		Motor.configAllowableClosedloopError(0, 0, 10);
		Motor.selectProfileSlot(0, 0);
		Motor.config_kP(0, Constants.Values.elevatorPidP, 10);
		Motor.config_kI(0, Constants.Values.elevatorPidI, 10);
		Motor.config_kD(0, Constants.Values.elevatorPidD, 10);
		Motor.config_kF(0, 0, 10);
		Motor.setNeutralMode(NeutralMode.Brake);
		Motor.configOpenloopRamp(0.25, 10);

		Motor.enableCurrentLimit(true);
		Motor.configForwardSoftLimitThreshold(35863, 10);
		Motor.configForwardSoftLimitEnable(true, 10);
		Motor.configPeakCurrentLimit(40, 10);
		Motor.configPeakCurrentDuration(100, 10);
		Motor.configContinuousCurrentLimit(30, 10);

		sensorCollection = new SensorCollection(Motor);

		setpointPrefs = Preferences.getInstance();

		setpointPrefs.putDouble("Elevator Bottom Height", Constants.Values.elevatorBottomHeight);
		setpointPrefs.putDouble("Elevator Switch Height", Constants.Values.elevatorSwitchHeight);
		setpointPrefs.putDouble("Elevator Low Mid Height", Constants.Values.elevatorLowMidHeight);
		setpointPrefs.putDouble("Elevator High Mid Height", Constants.Values.elevatorHighMidHeight);
		setpointPrefs.putDouble("Elevator Top Height", Constants.Values.elevatorTopHeight);

		flopUp();
	}

	// 1 = down (reverse), 0 = up (forward)
	public void flopUp() {
		elevatorSolenoid.set(DoubleSolenoid.Value.kForward);
		flop = 0;
	}

	public void flopDown() {
		elevatorSolenoid.set(DoubleSolenoid.Value.kReverse);
		flop = 1;
	}

	public void autozero() {
		if (sensorCollection.isRevLimitSwitchClosed() /* && !isZeroed */) {
			isZeroed = true;
			Motor.setSelectedSensorPosition(0, 0, 10);
		}
	}

	public double getCurrent() {
		elevatorCurrent = Robot.pdp.getCurrent(Constants.PDPPorts.elevatorTalon);
		return elevatorCurrent;
	}

	public double getPosition() {
		return Motor.getSelectedSensorPosition(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		// setDefaultCommand(new LockElevator());
	}

	public void stop() {
		// System.out.println("Stop Arm");
		Motor.set(ControlMode.PercentOutput, 0.0);
	}

	public void setPosition(double height) {
		Motor.set(ControlMode.Position, height);
	}

	public double getError() {
		return Motor.getClosedLoopError(0);
	}

	public void setVoltage(double volts) {
		Motor.set(ControlMode.PercentOutput, volts);
	}

	public void incrementIndex() {
		index++;
		if (index > heightList.length - 1) {
			index = heightList.length - 1;
		}
	}

	public void decrementIndex() {
		index--;
		if (index < 0) {
			index = 0;
		}
	}

	public double getHeightFromArray() {
		return heightList[index];
	}

	/*
	 * public void safeSetVoltage(double volts) { if (getCurrent() >
	 * Constants.Values.elevatorLimit) { Motor.set(ControlMode.PercentOutput, 0); }
	 * else { Motor.set(ControlMode.PercentOutput, volts); } }
	 */

	public void updateSmartDashboard() {
		if (delayCount == 10) {
			absolutePosition = Motor.getSelectedSensorPosition(0);// & 0xFFF;

			// DISPLAYED DATA
			// SmartDashboard.putNumber("TalonSRX Mode", Motor.getControlMode().value);
			// SmartDashboard.putNumber("Absolute Position", absolutePosition);
			SmartDashboard.putBoolean("Top limit switch", sensorCollection.isFwdLimitSwitchClosed());
			SmartDashboard.putBoolean("Bottom limit switch", sensorCollection.isRevLimitSwitchClosed());
			SmartDashboard.putBoolean("Elevator Zeroed", isZeroed);
			SmartDashboard.putNumber("ElevatorPIDError", Motor.getClosedLoopError(0));
			SmartDashboard.putNumber("Elevator Position ", Motor.getSelectedSensorPosition(0));
			SmartDashboard.putNumber("Elevator Current", getCurrent());
			SmartDashboard.putNumber("Elevator Voltage", Motor.getMotorOutputVoltage());

			// PREFERENCES SETTER
			Constants.Values.elevatorBottomHeight = setpointPrefs.getDouble("Elevator Bottom Height", 0);
			Constants.Values.elevatorSwitchHeight = setpointPrefs.getDouble("Elevator Switch Height", 0);
			Constants.Values.elevatorLowMidHeight = setpointPrefs.getDouble("Elevator Low Mid Height", 0);
			Constants.Values.elevatorHighMidHeight = setpointPrefs.getDouble("Elevator High Mid Height", 0);
			Constants.Values.elevatorTopHeight = setpointPrefs.getDouble("Elevator Top Height", 0);

			delayCount = 0;
		} else {
			delayCount++;
		}

	}
}
