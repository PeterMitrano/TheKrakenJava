
package org.usfirst.frc.team293.robot.commands;

import org.usfirst.frc.team293.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Opens the claw based on time
 */
public class OpenClaw extends Command {

	public OpenClaw() {
		requires(Robot.claw);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.claw.open();
	}

	/** see if you can make this better by using the encoder position rather than time
	 * on a real robot, this would be a very bad way to do things!
	 */
	protected boolean isFinished() {
		return Robot.claw.isOpen();
	}

	protected void end() {
		//don't stop in simulation, because they won't hold their position
		if (Robot.isReal()){
			Robot.claw.stop();
		}
	}

	protected void interrupted() {
		end();
	}
}
