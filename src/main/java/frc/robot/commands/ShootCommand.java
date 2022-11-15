// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class ShootCommand extends CommandBase {

  TurretSubsystem m_turret;

  // PID
  double kP = 0.0002;
  double kI = 0.00003;
  double iLimit = 500;
   
  double error;
  double errorSum;
  double rate;

  double currentSpeedRPM;

  double goalSpeedRPM = 1300;

  /** Creates a new Shoot. */
  public ShootCommand(TurretSubsystem turretSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
      m_turret = turretSubsystem;
      addRequirements(turretSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_turret.flywheelStop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double output = kP * error + kI * errorSum;

    error = goalSpeedRPM - currentSpeedRPM;

    if (error <= iLimit) {
      errorSum += error;
    }

    m_turret.flywheelRun(output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_turret.flywheelStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
