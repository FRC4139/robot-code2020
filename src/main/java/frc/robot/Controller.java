package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.AnalogInput;
public class Controller{
    // THE VALUES FOR THE DOUBLES BELOW NEED TO BE CONFIGURED MANUALLY

    //main controller
    private XboxController controller1;
    public Wheels wheels;
    //controller for color spinner
    private int intakeVal;
    private XboxController controller2;
    //private Conveyor conveyor;
    private int intConvPort1, intConvPort2;
    private double convDriveVal;
    //private double driveVal;
    //private DrawBridge drawbridge;
    //private int drumPWMChannel;
    //private double drumVal;
    private Intake intake;
    private int intakePort;
    private int wheelPort1, wheelPort2, wheelPort3, wheelPort4;
    //private HookExtension hook;
    private int hookPort;
    private Shooter revShoot;
    private Shooter shooter;
    private int shooterVal;
    private ColorWheel colorWheel;
    private int colorPort;
    private double colorWheelVal;
    private AHRS ahrs; 
    private AnalogInput m_ultrasonic;
    private ColorSensor colorSensor; 
    //private ColorArm colorArm;
    //private int servoPWMChannel;
    //private double upServoVal, downServoVal;

    public void controllerInit()
    {
        ahrs = new AHRS(SPI.Port.kMXP);
        m_ultrasonic = new AnalogInput(0);
        wheelPort1 = wheelPort1;
        wheelPort2 = wheelPort2;
        wheelPort3 = wheelPort3;
        wheelPort4 = wheelPort4;
        wheels = new Wheels(wheelPort1, wheelPort2, wheelPort3, wheelPort4);
        controller1 = new XboxController(0);
        controller2 = new XboxController(1);
        colorSensor = new ColorSensor();
        //conveyor = new Conveyor(intConvPort1,intConvPort2);
        //convDriveVal = ;
        //intConvPort1 = ;
        //intConvPort2 = ;
        //drumPWMChannel = ;
        //drumVal = ;
        //intakePort = ;
        //hookPort = ;
        //shooterVal = ;
        //colorPort = ;
        //colorWheelVal = ;
        //driveVal = ;
        //servoPWMChannel = ;
        //upServoVal = ;
        //downServoVal = ;
      //  drawbridge = new DrawBridge(drumPWMChannel);
        intake = new Intake(intakePort);
      //  hook = new HookExtension(hookPort);
        colorWheel = new ColorWheel(colorPort);
       // colorArm = new ColorArm(servoPWMChannel);
    }
    //conveyer
    public void UpdateTeleop() {
        
        // get values from the encoder. every 1 represents 1 rotation which is 2 pi r (6 pi) inches
        double frontLeftRotations = wheels.getRotations("fL");
        double backLeftRotations = wheels.getRotations("bR");

        //get values from the navx gyro to see which angle we are facing
        double angleFacing = ahrs.getAngle();

        //get value from the ultrasonic sensor mounted in the front of the robot
        double ultrasonicReading = getUltraSonicReading();

        //get value from the color sensor 
        String detectedColor = colorSensor.ReturnColor();

        //logic code below
        wheels.drive(controller1.getY(Hand.kLeft), controller1.getY(Hand.kRight));
        if (controller1.getAButtonPressed())
        {
            //conveyor.drive(driveVal);
            
        }
        //drum in 
        if(controller1.getYButtonPressed())
        {
            //drumVal = ;
            //drawbridge.SetVal(drumVal);
        }
        //drum out
        if(controller1.getBButtonPressed())
        {
            //drumVal = ;
            //drawbridge.SetVal(drumVal);
        }
        
        //1st controller right bumper; hook up (that sounds weird)
        if(controller1.getBumperPressed(Hand.kRight))
        {
            //hook.raise();
        }

        //1st controller left bumper; hook down
        if(controller1.getBumperPressed(Hand.kLeft))
        {
            //hook.lower();
        }
        //inverse wheels
        if(controller1.getXButtonPressed())
        {
            wheels.inverse();
        }
        //left trigger; intake
        if(controller1.getTriggerAxis(Hand.kLeft)>.1)
        {
            intake.drive(intakeVal);
        }
        // right trigger; controls shooter
        if(controller1.getTriggerAxis(Hand.kRight)>.1)
        {
            shooter.shootRun(shooterVal);
        }
        //2nd controller 
        //servo up
        if(controller2.getBButtonPressed())
        {
        // colorArm.SetVal(upServoVal);
        }

        if(controller2.getXButtonPressed())
        {
            
        }
        //servo down
        if(controller2.getAButtonPressed())
        {
        //    colorArm.SetVal(downServoVal);
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
        }
    }

    public double getUltraSonicReading() {
        return m_ultrasonic.getValue()*0.125f/2.54f;
    }

    public double GetAngleFacing() {
        return ahrs.getAngle();
    }
    
}
