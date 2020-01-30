package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
public class controller{

    //main controller
    private XboxController controller1;

    //controller for color spinner
    private XboxController controller2;

    public void controllerInit()
    {
        controller1 = new XboxController(0);
        controller2 = new XboxController(1);

    }
    //conveyer
    if(controller1.getAButtonPressed())
    {

    }
    //drum in 
    if(controller1.getYButtonPressed())
    {

    }
    //drum out
    if(controller1.getBButtonPressed())
    {

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
        
    }
    //left trigger; intake
    if(controller1.getTriggerAxis(Hand.kLeft)>.1)
    {

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