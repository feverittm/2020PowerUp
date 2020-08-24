package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 *
 */
	public class Climber extends SubsystemBase {
	
	private VictorSP climberMotor;
	private VictorSP climberMotor2;
	
	public Climber() {
		climberMotor = new VictorSP(Constants.Ports.climberVictorPort);
		climberMotor2 = new VictorSP(Constants.Ports.climberVictorPort2);
	}
		
	public void safeClimb() {
		if (Robot.pdp.getCurrent(Constants.PDPPorts.climber) > Constants.Values.climberLimit) {
			climberMotor.set(0);
			climberMotor2.set(0);
		} else {
			climberMotor.set(Constants.Values.climbspeed);
			climberMotor2.set(Constants.Values.climbspeed);
		}
	}

	public void safeUnclimb() {
		if (Robot.pdp.getCurrent(Constants.PDPPorts.climber) > Constants.Values.climberLimit) {
			climberMotor.set(0);
			climberMotor2.set(0);
		} else {
			climberMotor.set(-Constants.Values.climbspeed);
			climberMotor2.set(-Constants.Values.climbspeed);
		}
	}
	
	public void stopclimb() {
		climberMotor.set(0);
		climberMotor2.set(0);
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

