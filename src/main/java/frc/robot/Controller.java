package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
public class Controller{
    // THE VALUES FOR THE DOUBLES BELOW NEED TO BE CONFIGURED MANUALLY

    //main controller
    private XboxController xcontroller;
    private Wheels wheels;
    private int intakeVal;
    //    CONVEYOR CODE DELETED
    private Intake intake;
    private int intakePort;
    private int wheelPort1, wheelPort2, wheelPort3, wheelPort4;
    private HookExtension hook;
    private int hookPort;
    private int hookPortTwo;
    private int shooterPortOne, shooterPortTwo;
    //private Shooter revShoot;
    private Shooter shooter;
    private ColorWheel colorWheel;
    private int colorPort = 7;
    private AHRS ahrs;
    private AnalogInput m_ultrasonic;
    private ColorSensor colorSensor; 
    //private ColorArm colorArm;
    //private int servoPWMChannel;
    //private double upServoVal, downServoVal;
    private Boolean hookUp; 
    private String desiredColor; 
    private Boolean colorServoDeployed = false; 
    public Boolean spunTillThree = false;
    private Servo colorServo; 
    private VisionComp visionComp;

    private DigitalInput dioOne, dioTwo;

    public void controllerInit()
    {
        ahrs = new AHRS(SPI.Port.kMXP);
        m_ultrasonic = new AnalogInput(0);
      
  
        // fL, fR, bL, bR
        wheels = new Wheels(6,8,3,1);
        xcontroller = new XboxController(0);
        colorSensor = new ColorSensor();
        colorServo = new Servo(0); // UPDATE PORT ACCORDINGLY
        colorServo.set(0);
        shooter = new Shooter(7, 4);
        visionComp = new VisionComp();
        //intakePort = ;
        //hookPort = ;
        //shooterVal = ;
        //colorPort = ;
        //colorWheelVal = ;
        //visionParam = ;
        //vision = new Vision(visionParam);


        dioOne = new DigitalInput(0);
        dioTwo = new DigitalInput(1);

        intake = new Intake(2);

        hook = new HookExtension(9, 10);

        colorWheel = new ColorWheel(5, colorSensor);

        // colorArm = new ColorArm(servoPWMChannel);
        spunTillThree = false;
        hookUp = false;
    }
    
    public void UpdateTeleop() {
     // get values from the encoder. every 1 represents 1 rotation which is 2 pi r (6 pi) inches
        //double frontLeftRotations = wheels.getRotations("fL");
        //double backLeftRotations = wheels.getRotations("bR");

        //get values from the navx gyro to see which angle we are facing
        //double angleFacing = ahrs.getAngle();

        //get value from the ultrasonic sensor mounted in the front of the robot
        //double ultrasonicReading = getUltraSonicReading();

        //get value from the color sensor 
        //String detectedColor = colorSensor.ReturnColor();

        //get desired color from FMS
        desiredColor = DriverStation.getInstance().getGameSpecificMessage();

        //logic code below
        //SmartDashboard.putBoolean("spin next frame", colorWheel.spinNextFrame);
        SmartDashboard.putBoolean("servo deployed", colorServoDeployed);
        SmartDashboard.putBoolean("spun 3 times", spunTillThree);
        SmartDashboard.putString("gdata", desiredColor);
        SmartDashboard.putNumber("ultra sonic reading", getUltraSonicReading());
        SmartDashboard.putNumber("angle facing", getAngleFacing());
        
        wheels.drive(xcontroller.getY(Hand.kLeft), xcontroller.getY(Hand.kRight));
        
        if (xcontroller.getAButtonPressed() || colorWheel.spinNextFrame)
        {
            //System.out.println("a button pressed is " + xcontroller.getAButtonPressed() + "while the spin next frame is " + colorWheel.spinNextFrame);
            if (colorServoDeployed) {
                //System.out.println("Spun till 3? " + spunTillThree.toString());
                if (!spunTillThree) {
                    colorWheel.spinUntilThree(this);
                    if (colorWheel.spinNextFrame == false) {
                        colorServo.set(0);
                        colorServoDeployed = false;
                    }
                    
                    /*
                    SmartDashboard.putBoolean("spin 3", true);
                    SmartDashboard.putBoolean("spin color", false);
                    System.out.println("spinning till 3 since desiredColor is '" + desiredColor + "'");
                    */
                } else {
                    colorWheel.spinToColor(desiredColor);
                    if (colorWheel.spinNextFrame == false) {
                        colorServo.set(0);
                        colorServoDeployed = false;
                    }
                    /*
                    SmartDashboard.putBoolean("spin 3", false);
                    SmartDashboard.putBoolean("spin color", true);
                    System.out.println("spinning to color"); */
                }
            } else {
                colorServo.set(1);
                colorServoDeployed = true;
            }
            
        }
        //drum in 
        if(xcontroller.getYButtonPressed())
        {
            wheels.inverse();

        }
        if(xcontroller.getBButtonPressed())
        {
            if(hookUp)
            {
                hook.lower();
                hookUp = false;
            }
            if(!hookUp)
            {
                hook.raise();
                hookUp = true;
            }
        }


        if (xcontroller.getBButtonReleased()) {
            hook.stop();
        }
     
        

        
        if(xcontroller.getBumperPressed(Hand.kLeft))
        {
            intake.drive(0.85);
        }
        if(xcontroller.getBumperReleased(Hand.kLeft))
        {
            intake.drive(0);
        }
  
        if(xcontroller.getBumperPressed(Hand.kRight))
        {
            intake.drive(-0.85);
        }
        if(xcontroller.getBumperReleased(Hand.kRight))
        {
            intake.drive(0);
        }
        //inverse wheels
     
        //left trigger; revs up shooter
        if(xcontroller.getTriggerAxis(Hand.kLeft)>.1)
        {
            shooter.charge(0.8); //big wheel
        } else {
            shooter.charge(0);
        }
        // right trigger; controls shooter
        
        if(xcontroller.getTriggerAxis(Hand.kRight)>0)
        {
            shooter.fire(-0.4); //blue wheel
        } else {
            shooter.fire(0);
        }



        }

    private int autoSegment = 0;
    
    private Timer timer;
    private Timer timerTwo;

    public void UpdateAutonomous() {
        // code for all situations - DO NOT COMMENT OUT
        SmartDashboard.putNumber("Front Left Encoder Distance Travelled (ft)", getDistanceTravelled("fL"));
        SmartDashboard.putNumber("Back Right Encoder Distance Travelled (ft)", getDistanceTravelled("bR"));
        SmartDashboard.putNumber("Angle Facing From Gyro (degrees)", getAngleFacing());
        SmartDashboard.putNumber("Ultrasonic Reading (feet):", getUltraSonicReading() / 12);
        SmartDashboard.putNumber("Segment", autoSegment);

        //MAKE SURE NO ERRORS ARE PRESENT AFTER COMMENTING OUT LINES

        // THE FOLLOWING SEGMENT OF CODE CAN BE RUN WITHOUT ANY SENSORS (Move forward 1 sec, shoot)
        if (autoSegment == 0) {
            timerTwo.start();
            autoSegment++;
        } else if (autoSegment == 1) {
            if (timerTwo.get() < 1) {
                wheels.drive(0.7, 0.7);
                shooter.charge(0.8);
            } else {
                wheels.drive (0, 0);
                timer.start();
                autoSegment++;
            }
        } else if (autoSegment == 2) {
            wheels.drive(0, 0);
            shooter.charge(0.8); // big
            
            
            if (timer.get() < 1 && timer.get() > 0.5) {
                shooter.fire(-0.4);
            } else if (timer.get() > 2 && timer.get() < 2.5) {
                shooter.fire(-0.4);
            } else if (timer.get() > 3.5 && timer.get() < 4) {
                shooter.fire(-0.4);
            } else if (timer.get() > 4) {
                shooter.fire(0);
                shooter.charge(0);
                intake.drive(0);
                autoSegment++;
            } else {
                shooter.fire(0);
                intake.drive(0.85);
            }
            
        }

        // THE CODE SEGMENT BELOW REQUIRES ULTRASONIC SENSOR
        /*
        if (autoSegment == 0) {
            if (getUltraSonicReading() > 100) {
                wheels.drive(0.7, 0.7);
                shooter.charge(0.8); // big
            } else if (getUltraSonicReading() < 100) {
                wheels.drive(0, 0);
                timer.start();
                autoSegment++;
            } 
        } else if (autoSegment == 1) {
            wheels.drive(0, 0);
            shooter.charge(0.8); // big
            
            
            if (timer.get() < 1 && timer.get() > 0.5) {
                shooter.fire(-0.4);
            } else if (timer.get() > 2 && timer.get() < 2.5) {
                shooter.fire(-0.4);
            } else if (timer.get() > 3.5 && timer.get() < 4) {
                shooter.fire(-0.4);
            } else if (timer.get() > 4) {
                shooter.fire(0);
                shooter.charge(0);
                intake.drive(0);
                autoSegment++;
            } else {
                shooter.fire(0);
                intake.drive(0.85);
            }
            
        }
        

        //ALL THE CODE BELOW REQUIRES ENCODERS (CASES 1 + 2)
        /*
        // case 1 (right in front of target) POSITION SO BACK OF ROBOT IS TOUCHING LINE. MOVE FORWARD ONE FOOT AND SHOOT

            if (getDistanceTravelled("fL") <= 1 && autoSegment == 0) {
                wheels.drive(0.7, 0.7);
                shooter.charge(0.8);
            } else if (autoSegment == 1) {
                wheels.drive(0, 0);
                shooter.charge(0.8); // big
                
                if (timer.get() == 0) {
                    timer.start();
                }
                
                if (timer.get() < 1 && timer.get() > 0.5) {
                    shooter.fire(-0.4);
                } else if (timer.get() > 2 && timer.get() < 2.5) {
                    shooter.fire(-0.4);
                } else if (timer.get() > 3.5 && timer.get() < 4) {
                    shooter.fire(-0.4);
                } else if (timer.get() > 4) {
                    shooter.fire(0);
                    shooter.charge(0);
                    intake.drive(0);
                } else {
                    shooter.fire(0);
                    intake.drive(0.85);
                }
                
            } else {
                wheels.drive(0, 0);
                autoSegment++;
            }
            
        
            // case 2 (on the side closer to target) POSITION SO FRONT OF ROBOT IS TOUCHING LINE. MAKE SURE ROBOT IS FACING STRAIGHT AHEAD. IT WILL DRIVE BACK AND PICK UP 3 BALLS 
        /*
            if (autoSegment == 0) {
            if (Math.abs(getAngleFacing()) < 15) {
                wheels.drive(-.7,.7); //turn left
            } else {
                wheels.drive(0,0);
                autoSegment++;
            }
        } else if (autoSegment == 1 && timer.get() < 5) {
            wheels.drive(0,0);
            if (timer.get() == 0) {
                timer.start();
            }
            //shoot the three pre loaded balls
            if (timer.get() < 1 && timer.get() > 0.5) {
                shooter.fire(-0.4);
            } else if (timer.get() > 2 && timer.get() < 2.5) {
                shooter.fire(-0.4);
            } else if (timer.get() > 3.5 && timer.get() < 4) {
                shooter.fire(-0.4);
            } else if (timer.get() > 4) {
                shooter.fire(0);
                shooter.charge(0);
                autoSegment++;
                intake.drive(0);
            } else {
                shooter.fire(0);
                intake.drive(0.85);
            }
        } else if (autoSegment == 2) {
            if (Math.abs(getAngleFacing()) > 1) {
                wheels.drive(0.7,-0.7); //turn right
            } else {
                wheels.drive(0, 0);
                autoSegment++;
            }
        } else if (autoSegment == 3) {
            wheels.resetRotations();
            autoSegment++;
        } else if (autoSegment == 4) {
            //BACK 10 FT
            if (Math.abs(getDistanceTravelled("fL")) < 10) {
                wheels.drive(-0.5, -0.5);
                intake.drive(0.85);
            } else {
                wheels.drive(0, 0);
                intake.drive(0);
                autoSegment++;
            }
        } else if (autoSegment == 5) {
            wheels.resetRotations();
            autoSegment++;
        } else if (autoSegment == 6) {
            //FORWARD 10 FT
            if (Math.abs(getDistanceTravelled("fL")) < 10) {
                wheels.drive(0.7, 0.7);
                intake.drive(0.85);
            } else {
                wheels.drive(0, 0);
                intake.drive(0);
                autoSegment++;
            }
        } else if (autoSegment == 7) {
            if (Math.abs(getAngleFacing()) < 15) {
                wheels.drive(-.7,.7); //turn left
            } else {
                wheels.drive(0,0);
                autoSegment++;
            }
        } else if (autoSegment == 8 && timer.get() < 5) {
            wheels.drive(0,0);
            if (timerTwo.get() == 0) {
                timerTwo.start();
            }
            //shoot the three pre loaded balls
            if (timerTwo.get() < 1 && timerTwo.get() > 0.5) {
                shooter.fire(-0.4);
            } else if (timerTwo.get() > 2 && timerTwo.get() < 2.5) {
                shooter.fire(-0.4);
            } else if (timerTwo.get() > 3.5 && timerTwo.get() < 4) {
                shooter.fire(-0.4);
            } else if (timerTwo.get() > 4) {
                shooter.fire(0);
                shooter.charge(0);
                autoSegment++;
                intake.drive(0);
            } else {
                shooter.fire(0);
                intake.drive(0.85);
            }
        }
        */
        
        
    }

    public int AutonomousTurnCheck() {
        if (!dioOne.get() && !dioTwo.get()) {
            visionComp.compute(2); // straight
            return 2;
        }
        
        if (!dioOne.get()) {
            visionComp.compute(0); // left
            return 0;
        }
        if (!dioTwo.get()) {
            visionComp.compute(1); //  right
            return 1;
        } 

        if (dioOne.get() && dioTwo.get()) 
        {
            visionComp.compute(3); // none
            return 3;
        }

        return -1; 
    }

    public double getUltraSonicReading()
    {
        return m_ultrasonic.getValue()*0.125f/2.54f;
    }

    public double getAngleFacing() 
    {
        return ahrs.getAngle();
    }
    
    public double getDistanceTravelled(String pos) {
        // return                  rotations * 2 * pi * r * (1 foot / 12 inches)
        return wheels.getRotations(pos) * 2 * 3.1415926535 * 3 / 12;
    }
}