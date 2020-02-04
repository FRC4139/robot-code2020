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
    private drawBridge drawbridge;
    private int drumPWMChannel;
    private double drumVal;
    private Intake intake;
    private int intakePort;
    private int wheelPort1, wheelPort2, wheelPort3, wheelPort4;

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
        drawbridge = new drawBridge(drumPWMChannel);
        intake = new Intake(intakePort);
    }
    //conveyer
    if(controller1.getAButtonPressed())
    {
        conveyor.drive()
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

    }

    //1st controller left bumper; hook down
    if(controller1.getBumperPressed(Hand.kLeft))
    {

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
        
    }
    //2nd controller 
    //servo up
    if(controller2.getBButtonPressed())
    {

    }

    if(controller2.getXButtonPressed())
    {

    }
    //fly wheel, servo down
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
    if(controller2.getTriggerAxis(hand.kLeft)>.1)
    {

    }
}
}