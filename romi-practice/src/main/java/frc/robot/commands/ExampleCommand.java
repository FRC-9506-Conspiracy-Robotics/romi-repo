// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ExampleCommand extends Command {
  public enum Phase {
    LEG,
    TURN;
  }

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final RomiDrivetrain m_subsystem;
  private Timer m_timer = new Timer();
  Phase current_phase = Phase.LEG;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(RomiDrivetrain subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.current_phase = Phase.LEG;
    this.m_timer.restart();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (this.current_phase == Phase.LEG) {
      this.m_subsystem.arcadeDrive(0.5, 0.0);

      if (this.m_timer.get() > 1.0) {
        this.m_timer.restart();
        this.current_phase = Phase.TURN;
      }
    } else if (this.current_phase == Phase.TURN) {
      this.m_subsystem.arcadeDrive(0.0, 0.5);

      if (this.m_timer.get() > 1.0) {
        this.m_timer.restart();
        this.current_phase = Phase.LEG;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
