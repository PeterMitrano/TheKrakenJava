package org.usfirst.frc.team293.robot.commands;

import org.usfirst.frc.team293.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The command to be run in autonomous
 */
public class Auto extends CommandGroup {

	public Auto() {
		grabToteAndBin();
	}

	/**
	 * Picks up Bin, stacks it on the next tote, and scores in the auto zone
	 * */
	public void grabToteAndBin() {
		addParallel(new OpenClaw());
		addParallel(new SetElevatorPosition(Elevator.getBin));
		addSequential(new DriveStraightTillTote());
		addSequential(new SetElevatorPosition(Elevator.liftBin)); // bin
																	// acquired

		addSequential(new DriveStraightTillTote());
		addSequential(new SetElevatorPosition(Elevator.getTote));
		addParallel(new CloseClaw()); // tote acquired

		addSequential(new TurnThroughAngle(90));
		addSequential(new DriveStraightDistance(3));

		addParallel(new OpenClaw());
		addSequential(new DriveStraightDistance(-0.5)); // tote + bin + robot
														// scored!
	}

	/** simply turns and enters the auto zone */
	public void scoreRobot() {
		addSequential(new TurnThroughAngle(90));
		addSequential(new DriveStraightDistance(7));
	}
}