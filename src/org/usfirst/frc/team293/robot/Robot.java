package org.usfirst.frc.team293.robot;

import org.usfirst.frc.team293.robot.commands.Auto;
import org.usfirst.frc.team293.robot.subsystems.Claw;
import org.usfirst.frc.team293.robot.subsystems.DriveTrain;
import org.usfirst.frc.team293.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * THIS PROJECT IS MEANT TO BE RUN IN GAZEBO

 * To setup gazebo, see the @see <a href="http://wpilib.screenstepslive.com/s/4485/m/23353">FRCSim Installation Guide</a>
 * A standard USB Xbox gamepad, refer to the OI class for control instructions
 *
 *  @Author Peter Mitrano
 *
 * This is the main class where the robot state is controlled
 * Unlike in the real game, these states will not time out
 * Test mode activates all the Live Window tools in the SmartDashboard, and should be used for tuning control loops, and testing commands
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Claw claw;
	public static Elevator elevator;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		driveTrain = new DriveTrain();
		claw = new Claw();
		elevator = new Elevator();
		oi = new OI();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		elevator.disable();
		elevator.stop();
		claw.open();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = new Auto();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
