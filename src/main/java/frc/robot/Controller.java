package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;
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
    private Shooter revShoot;
    private Shooter shooter;
    private int shooterVal;
    private ColorWheel colorWheel;
    private int colorPort = 7;
    private double colorWheelVal;
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
    private PWMVictorSPX victorMotors;
    

    public void controllerInit()
    {
        ahrs = new AHRS(SPI.Port.kMXP);
        m_ultrasonic = new AnalogInput(0);
        //wheelPort1 = wheelPort1;
        //wheelPort2 = wheelPort2;
        //wheelPort3 = wheelPort3;
        //wheelPort4 = wheelPort4;
        wheels = new Wheels(wheelPort1, wheelPort2, wheelPort3, wheelPort4);
        xcontroller = new XboxController(0);
        colorSensor = new ColorSensor();
        colorServo = new Servo(0); // UPDATE PORT ACCORDINGLY
        colorServo.set(0);
        //intakePort = ;
        //hookPort = ;
        //shooterVal = ;
        //colorPort = ;
        //colorWheelVal = ;
        //visionParam = ;
        //vision = new Vision(visionParam);
        //input 3 ports for victorspx construction
        victorMotors = new PWMVictorSPX();

        intake = new Intake(intakePort);


        colorWheel = new ColorWheel(colorPort, colorSensor);

        // colorArm = new ColorArm(servoPWMChannel);

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
        wheels.drive(xcontroller.getY(Hand.kLeft), xcontroller.getY(Hand.kRight));
        if (xcontroller.getAButtonPressed() || colorWheel.spinNextFrame)
        {
            if (colorServoDeployed) {
                //System.out.println("Spun till 3? " + spunTillThree.toString());
                if (!spunTillThree) {
                    colorWheel.spinUntilThree(this);
                    /*
                    SmartDashboard.putBoolean("spin 3", true);
                    SmartDashboard.putBoolean("spin color", false);
                    System.out.println("spinning till 3 since desiredColor is '" + desiredColor + "'");
                    */
                } else {
                    colorWheel.spinToColor(desiredColor);
                    /*
                    SmartDashboard.putBoolean("spin 3", false);
                    SmartDashboard.putBoolean("spin color", true);
                    System.out.println("spinning to color"); */
                }
            } else {
                colorServo.set(0.5);
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
      
        

        //1st controller left bumper; hook down
        if(xcontroller.getBumperPressed(Hand.kLeft))
        {
            intake.drive(intakeVal);
        }
        //inverse wheels
        if(xcontroller.getXButtonPressed())
        {
            //vision something
        }
        //left trigger; revs up shooter
        if(xcontroller.getTriggerAxis(Hand.kLeft)>.1)
        {
            revShoot.charge(0.6);
        }
        // right trigger; controls shooter
        
        if(xcontroller.getTriggerAxis(Hand.kRight)>.1 && xcontroller.getTriggerAxis(Hand.kLeft)>.1)
        {
            shooter.fire(); //kick fuel
        }



        }

    public double getUltraSonicReading()
    {
        return m_ultrasonic.getValue()*0.125f/2.54f;
    }

    public double getAngleFacing() 
    {
        return ahrs.getAngle();
    }
    
}