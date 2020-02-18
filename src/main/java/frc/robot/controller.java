package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
public class Controller{
    // THE VALUES FOR THE DOUBLES BELOW NEED TO BE CONFIGURED MANUALLY

    //main controller
    //private XboxController controller1;
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
    private boolean hookUp;
    
    //private Vision vision;
    //private int visionParam;
    public void controllerInit()
    {
        wheels = new Wheels(wheelPort1, wheelPort2, wheelPort3, wheelPort4);
        //controller1 = new XboxController(0);
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
    
    public void update(XboxController controller1) {
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
    
}
