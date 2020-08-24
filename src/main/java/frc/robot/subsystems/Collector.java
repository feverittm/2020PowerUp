package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Collector extends SubsystemBase {
	private VictorSP leftmotor, rightmotor;
	private AnalogInput leftinput = new AnalogInput(Constants.Ports.leftCollectorSensorInput);
	private AnalogInput rightinput = new AnalogInput(Constants.Ports.rightCollectorSensorInput);
	public static double leftVoltage;
	public static double rightVoltage;
	public boolean gotCube;

	public Collector() {
		leftmotor = new VictorSP(Constants.Ports.leftCollectorPort);
		rightmotor = new VictorSP(Constants.Ports.rightCollectorPort);
	}

	public void collect(double leftspeed, double rightspeed) {
		// ONE VALUE INVERTED TO COLLECT
		leftmotor.set(leftspeed);
		rightmotor.set(-rightspeed);
	}

	public double getAvgLeftVoltage() {
		leftVoltage = leftinput.getAverageVoltage();
		return leftVoltage;
	}

	public double getAvgRightVoltage() {
		rightVoltage = rightinput.getAverageVoltage();
		return rightVoltage;
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("rightCollectorSensor", getAvgRightVoltage());
		SmartDashboard.putNumber("leftCollectorSensor", getAvgLeftVoltage());
		SmartDashboard.putBoolean("Got Cube?", gotCube);
	}
}
