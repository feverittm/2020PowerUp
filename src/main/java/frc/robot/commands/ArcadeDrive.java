package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 *
 */
public class ArcadeDrive extends CommandBase {
	@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
	private final DriveTrain m_drivetrain;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public ArcadeDrive(DriveTrain drivetrain) {
		m_drivetrain = drivetrain;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(drivetrain);
	}

	double initAngle;
	double deltaTheta;
	double correction;
	double kTheta = 0.01;

	double leftEncoderDist;
	double rightEncoderDist;

	@Override
	public void initialize() {
		// Robot.drivetrain.resetEncoders();
		m_drivetrain.setCoast();
		initAngle = m_drivetrain.getAHRSAngle();
	}

	@Override
	public void execute() {
		// leftEncoderDist =
		// Robot.drivetrain.getLeftEncoderTicks()*RobotMap.Values.inchesPerTick;
		// rightEncoderDist =
		// Robot.drivetrain.getRightEncoderTicks()*RobotMap.Values.inchesPerTick;

		if (m_drivetrain.decellOn) {
			// using decell code
			// simple correction w/ multiplier and squared
			m_drivetrain.driveDecell(
					((Robot.m_oi.getLeftY()) + (Robot.m_oi.getRightX()))
							* Math.abs(((Robot.m_oi.getLeftY()) + (Robot.m_oi.getRightX()))),
					((Robot.m_oi.getLeftY() * 0.85) - Robot.m_oi.getRightX() * 0.85)
							* Math.abs(((Robot.m_oi.getLeftY() * 0.85) - Robot.m_oi.getRightX() * 0.85)));

			// correction using gyro
			/*
			 * if (Robot.m_oi.joystickDeadband(Math.abs(Robot.m_oi.getRightX())) != 0) {
			 * Robot.drivetrain.setVoltages(Robot.m_oi.getLeftY() + Robot.m_oi.getRightX(),
			 * Robot.m_oi.getLeftY() - Robot.m_oi.getRightX()); initAngle =
			 * Robot.drivetrain.getAHRSAngle();
			 * 
			 * } else {
			 * 
			 * deltaTheta = Robot.drivetrain.getAHRSAngle() - initAngle; correction =
			 * deltaTheta*kTheta; Robot.drivetrain.setVoltages((Robot.m_oi.getLeftY() +
			 * Robot.m_oi.getRightX()) - correction, (Robot.m_oi.getLeftY() -
			 * Robot.m_oi.getRightX()) + correction); }
			 * 
			 * SmartDashboard.putNumber("Arcade drive initAngle", initAngle);
			 * SmartDashboard.putNumber("Arcade drive deltatheta", deltaTheta);
			 * SmartDashboard.putNumber("Arcade drive correction", correction);
			 */

		} else {
			// simple correction w/ multiplier and squared
			m_drivetrain.setVoltages(
					((Robot.m_oi.getLeftY()) + (Robot.m_oi.getRightX()))
							* Math.abs((Robot.m_oi.getLeftY()) + (Robot.m_oi.getRightX())),
					((Robot.m_oi.getLeftY() * 0.85) - (Robot.m_oi.getRightX() * 0.85))
							* Math.abs((Robot.m_oi.getLeftY() * 0.85) - (Robot.m_oi.getRightX() * 0.85)));
		}

		// no correction
		/*
		 * Robot.drivetrain.setVoltages(Robot.m_oi.getLeftY() + Robot.m_oi.getRightX(),
		 * Robot.m_oi.getLeftY() - Robot.m_oi.getRightX());
		 */

		// correction using encoders (unfinished)
		/*
		 * if (Math.abs(Robot.m_oi.getRightX()) != 0) {
		 * Robot.drivetrain.setVoltages(Robot.m_oi.getLeftY() + Robot.m_oi.getRightX(),
		 * Robot.m_oi.getLeftY() - Robot.m_oi.getRightX()); }
		 */

		// correction using gyro
		/*
		 * if (Math.abs(Robot.m_oi.getRightX()) != 0) {
		 * Robot.drivetrain.setVoltages(Robot.m_oi.getLeftY() + Robot.m_oi.getRightX(),
		 * Robot.m_oi.getLeftY() - Robot.m_oi.getRightX()); initAngle =
		 * Robot.drivetrain.getAHRSAngle();
		 * 
		 * } else {
		 * 
		 * deltaTheta = Robot.drivetrain.getAHRSAngle() - initAngle; correction =
		 * deltaTheta*kTheta; Robot.drivetrain.setVoltages((Robot.m_oi.getLeftY() +
		 * Robot.m_oi.getRightX()) - correction, (Robot.m_oi.getLeftY() -
		 * Robot.m_oi.getRightX()) + correction); }
		 * 
		 * SmartDashboard.putNumber("Arcade drive initAngle", initAngle);
		 * SmartDashboard.putNumber("Arcade drive deltatheta", deltaTheta);
		 * SmartDashboard.putNumber("Arcade drive correction", correction);
		 */

	}

	public void end() {
		m_drivetrain.setVoltages(0, 0);
	}

	public void interrupted() {
		end();
	}
}
