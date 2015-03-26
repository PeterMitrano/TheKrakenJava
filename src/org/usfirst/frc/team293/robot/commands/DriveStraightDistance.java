package org.usfirst.frc.team293.robot.commands;

import org.usfirst.frc.team293.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive forward a given distance, base on encoder values
 * You should try to make this better, by using a control loop (P loop would work) based on the Gyro!
 */
public class DriveStraightDistance extends Command {

	double distance, startPosition;

	/** @param distance the distance to travel in meters */
	public DriveStraightDistance(double distance) {
		requires(Robot.driveTrain);
		this.distance = distance;
	}

	protected void initialize() {
		this.startPosition = Robot.driveTrain.getAverageDistance();
	}

	/** drives forward if distance is positive */
	protected void execute() {
		if (distance > 0) {
			Robot.driveTrain.drive(0.90, 0.90);
		} else {
			Robot.driveTrain.drive(-0.90, -0.90);
		}
	}

	/** comparing absolute value returns true when it's very enough */
	protected boolean isFinished() {
		return Math.abs((Robot.driveTrain.getAverageDistance() - startPosition)
				- distance) < 0.05;
	}

	protected void end() {
		Robot.driveTrain.stop();
	}

	protected void interrupted() {
		end();
	}
}