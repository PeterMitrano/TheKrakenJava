package org.usfirst.frc.team293.robot.commands;

import org.usfirst.frc.team293.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive straight until the ultrasonic sensor detects a tote or Bin
 */
public class DriveStraightTillTote extends Command {

	public DriveStraightTillTote() {
		requires(Robot.driveTrain);
	}

	/** makes sure the claw is open before it drives */
	protected void initialize() {
		if (!Robot.claw.isOpen()){
			(new OpenClaw()).start();
		}
	}

	protected void execute() {
		Robot.driveTrain.drive(0.7, 0.7);
	}

	protected boolean isFinished() {
		return Robot.driveTrain.nearTote();
	}

	protected void end() {
		Robot.driveTrain.stop();
	}

	protected void interrupted() {
		end();
	}
}
