package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
public class controller{

    //main controller
    private XboxController controller1;
    private Wheels wheels;
    //controller for color spinner
    private XboxController controller2;
    private Conveyor conveyor;
    private int intConvPort1, intConvPort2;
    private double convDriveVal;
    private double driveVal;
    private drawBridge drawbridge;
    private int drumPWMChannel;
    private double drumVal;
    private Intake intake;
    private int intakePort;
    private int wheelPort1, wheelPort2, wheelPort3, wheelPort4;
    private hookExtension hook;
    private int hookPort;
    private shooter shooter;
    private int shooterVal;
    private flyWheel colorWheel;
    private int colorPort;
    private double colorWheelVal;
    private colorArm colorArm;
    private int servoPWMChannel;
    private double upServoVal, downServoVal;

    public void controllerInit()
    {
        wheels = new Wheels(wheelPort1, wheelPort2, wheelPort3, wheelPort4);
        controller1 = new XboxController(0);
        controller2 = new XboxController(1);
        conveyor = new Conveyor(intConvPort1,intConvPort2);
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
        drawbridge = new drawBridge(drumPWMChannel);
        intake = new Intake(intakePort);
        hook = new hookExtension(hookPort);
        colorWheel = new flyWheel(colorPort);
        colorArm = new colorArm(servoPWMChannel);
    }
    //conveyer
    if(controller1.getAButtonPressed())
    {
        conveyor.drive(driveVal);
    }
    //drum in 
    if(controller1.getYButtonPressed())
    {
        //drumVal = ;
        drawbridge.SetVal(drumVal);
    }
    //drum out
    if(controller1.getBButtonPressed())
    {
        //drumVal = ;
        drawbridge.SetVal(drumVal);
    }
    
    //1st controller right bumper; hook up (that sounds weird)
    if(controller1.getBumperPressed(Hand.kRight))
    {
        hook.raise();
    }

    //1st controller left bumper; hook down
    if(controller1.getBumperPressed(Hand.kLeft))
    {
        hook.lower();
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
        colorArm.setVal(upServoVal);
    }

    if(controller2.getXButtonPressed())
    {
        
    }
    //servo down
    if(controller2.getAButtonPressed())
    {
        colorArm.setVal(downServoVal);
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
    if(controller2.getTriggerAxis(hand.kLeft)>.1)
    {
        colorWheel.flyRun(colorWheelVal);
    }
}
}