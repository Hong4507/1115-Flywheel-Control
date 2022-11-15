// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  private final WPI_TalonSRX LeftFront = new WPI_TalonSRX(DriveConstants.kLeftFrontMotorID);
  private final WPI_TalonSRX LeftRear = new WPI_TalonSRX(DriveConstants.kLeftRearMotorID);
  private final WPI_TalonSRX RightFront = new WPI_TalonSRX(DriveConstants.kRightFrontMotorID);
  private final WPI_TalonSRX RightRear = new WPI_TalonSRX(DriveConstants.kRightRearMotorID);

  private MecanumDrive m_drive = new MecanumDrive(LeftFront, LeftRear, RightFront, RightRear);

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveCartasian(double speed, double move, double turn) {
    m_drive.driveCartesian(speed, move, turn);
  }

  public void stop() {
    m_drive.driveCartesian(0, 0, 0);
  }
}
