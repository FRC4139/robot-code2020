/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//TESTING MY BRANCH lk
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private XboxController controller;
  //private Wheels wheels;
  private WPI_TalonSRX testTalon,testTalon2;
  private float currentSpeed = 0.0f; 

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    controller = new XboxController(0);
    //wheels = new Wheels(3, 8, 9, 10);
    testTalon = new WPI_TalonSRX(3);
    testTalon2 = new WPI_TalonSRX(8);
  }

  /*
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override  
  public void teleopPeriodic() {
    // on the kitbot, needs to be eased in
    // just an issue with these motors?
    if (controller.getAButtonReleased()) {
      currentSpeed += 0.01;
    } 
    if (controller.getBButtonReleased()) {
      currentSpeed -= 0.01;
    } 
    if (controller.getXButtonReleased()) {
      currentSpeed += 0.1;
    } 
    if (controller.getYButtonReleased()) {
      currentSpeed -= 0.1;
    } 

    if (controller.getBumperReleased(Hand.kRight)) {
      currentSpeed = 0;
    }

    testTalon.set(currentSpeed);
    testTalon2.set(-currentSpeed);
    //wheels.drive(controller.getY(Hand.kLeft), controller.getY(Hand.kRight));
    SmartDashboard.putString("[left joystick] ", "value: " + controller.getY(Hand.kLeft));
    SmartDashboard.putString("[right joystick] ", "value: " + controller.getY(Hand.kRight));
    SmartDashboard.putNumber("speed", currentSpeed);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }
}