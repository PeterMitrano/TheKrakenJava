package org.usfirst.frc.team293.robot.commands;

import org.usfirst.frc.team293.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns until a specific angle is reached. Try making this better by using a
 * control loop (P loop would work) to make it more accurate!
 */
public class TurnThroughAngle extends Command {

	double angle;
	double startingAngle;
	double endAngle;

	/**
	 * @param angle
	 *            the angle in degrees. Positive is counter-clockwise
	 */
	public TurnThroughAngle(double angle) {
		requires(Robot.driveTrain);
		this.angle = angle;

	}

	protected void initialize() {
		startingAngle = Robot.driveTrain.getAngle();
		// getAngle goes from -180 to 180, we must compensate for this
		if (angle + startingAngle >= 180) {
			endAngle = (angle + startingAngle) % 180 - 180;
		} else {
			endAngle = angle + startingAngle;
		}

	}

	/** turns at full power */
	protected void execute() {
		if (angle > 0) {
			Robot.driveTrain.drive(-0.95, 0.95);
		} else {
			Robot.driveTrain.drive(0.95, -0.95);
		}
	}

	protected boolean isFinished() {
		return Math.abs(Robot.driveTrain.getAngle() - endAngle) < 0.5;
	}

	protected void end() {
		Robot.driveTrain.stop();
	}

	protected void interrupted() {
	}
}
