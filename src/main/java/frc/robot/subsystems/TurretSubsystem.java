// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {

  private final CANSparkMax slaveFlywheel = new CANSparkMax(TurretConstants.kSlaveFlywheelID, MotorType.kBrushless);
  private final CANSparkMax masterFlywheel = new CANSparkMax(TurretConstants.kMasterFlywheelID, MotorType.kBrushless);

  /** Creates a new TurretSubsystem. */
  public TurretSubsystem() {
    masterFlywheel.setInverted(true);
    slaveFlywheel.setInverted(false);
    slaveFlywheel.follow(masterFlywheel, true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Flywheel Current Velocity (RPM)", getCurrentFlywheelRPM());
  }

  public void flywheelRun(double speed) {
    masterFlywheel.set(speed);
  }

  public void flywheelStop() {
    masterFlywheel.set(0);
    slaveFlywheel.set(0);
  }

  public double getCurrentFlywheelRPM() {
    double flywheelVelocity = (masterFlywheel.getEncoder().getVelocity() + slaveFlywheel.getEncoder().getVelocity()) / 2;
    return flywheelVelocity;
  }
}
