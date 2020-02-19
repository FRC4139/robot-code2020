package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
public class Controller{
    // THE VALUES FOR THE DOUBLES BELOW NEED TO BE CONFIGURED MANUALLY

    //main controller
    private XboxController controller1;
    private Wheels wheels;
    private int intakeVal;
    //    CONVEYOR CODE DELETED
    private Intake intake;
    private int intakePort;
    private int wheelPort1, wheelPort2, wheelPort3, wheelPort4;
    //private HookExtension hook;
    private int hookPort;
    private Shooter shooter;
    private int shooterVal;
    private ColorWheel colorWheel;
    private int colorPort;
    private double colorWheelVal;
    private AHRS ahrs;
    private AnalogInput m_ultrasonic;
    private boolean hookUp;
    
    //private Vision vision;
    //private int visionParam;
    public void controllerInit()
    {
        ahrs = new AHRS(SPI.Port.kMXP);
        m_ultrasonic = new AnalogInput(0);
        //wheelPort1 = wheelPort1;
        //wheelPort2 = wheelPort2;
        //wheelPort3 = wheelPort3;
        //wheelPort4 = wheelPort4;
        wheels = new Wheels(wheelPort1, wheelPort2, wheelPort3, wheelPort4);
        controller1 = new XboxController(0);
        //intakePort = ;
        //hookPort = ;
        //shooterVal = ;
        //colorPort = ;
        //colorWheelVal = ;
        //visionParam = ;
        //vision = new Vision(visionParam);
        intake = new Intake(intakePort);
        //  hook = new HookExtension(hookPort);
        colorWheel = new ColorWheel(colorPort);
        // colorArm = new ColorArm(servoPWMChannel);
        hookUp = true;
    }
    
    public void updateTeleop() {
     // get values from the encoder. every 1 represents 1 rotation which is 2 pi r (6 pi) inches
        double frontLeftRotations = wheels.getRotations("fL");
        double backLeftRotations = wheels.getRotations("bR");

    //get values from the navx gyro to see which angle we are facing
    double angleFacing = ahrs.getAngle();

    //get value from the ultrasonic sensor mounted in the front of the robot
    double ultrasonicReading = getUltraSonicReading();

    //logic code below
    wheels.drive(controller1.getY(Hand.kLeft), controller1.getY(Hand.kRight));
    if (controller1.getAButtonPressed())
    {

    }
    //drum in 
    if(controller1.getYButtonPressed())
    {
        wheels.inverse();
    }
    //drum out
    if(controller1.getBButtonPressed())
    {
        if(hookUp)
            hook.lower();
        else
            hook.raise();        
    }
    
    //1st controller right bumper; hook up (that sounds weird)
    if(controller1.getBumperPressed(Hand.kRight))
    {
        
    }

    //1st controller left bumper; hook down
    if(controller1.getBumperPressed(Hand.kLeft))
    {
        intake.drive(intakeVal);
    }
    //inverse wheels
    if(controller1.getXButtonPressed())
    {
        //vision something
    }
    //left trigger
    if(controller1.getTriggerAxis(Hand.kLeft)>.1)
    {
        colorWheel.flyRun(colorWheelVal);
    }
    // right trigger; controls shooter
    if(controller1.getTriggerAxis(Hand.kRight)>.1)
    {
        shooter.shootRun(shooterVal);
    }
    if(controller1.getTriggerAxis(Hand.kRight)>.1 && controller1.getTriggerAxis(Hand.kLeft)>.1)
    {
        shooter.loader();
    }


    //2nd controller 
    //servo up
    /*
    if(controller2.getBButtonPressed())
    {
       
    }

    if(controller2.getXButtonPressed())
    {
        
    }
    //servo down
    if(controller2.getAButtonPressed())
    {
    
    }

    if(controller2.getYButton())
    {

    }

    //2nd controller right bumper
    if(controller2.getBumperPressed(Hand.kRight))
    {

    }

    //2nd controller left bumper
    if(controller2.getBumperPressed(Hand.kLeft))
    {

    }
    //right trigger; color spinner right
    if(controller2.getTriggerAxis(Hand.kRight)>.1)
    {

    }
    //left trigger; color spinner left
    if(controller2.getTriggerAxis(Hand.kLeft)>.1)
    {
        colorWheel.flyRun(colorWheelVal);
    }*/
    }
    private double getUltraSonicReading()
    {
        return m_ultrasonic.getValue()*0.125f/2.54f;
    }
    
}

