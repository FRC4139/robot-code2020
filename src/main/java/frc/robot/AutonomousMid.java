package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import java.lang.Math;

public class AutonomousMid{

    public boolean switch1 = false;
    public boolean switch2 = false;
    public boolean switch3 = false;

    public String position;
    public int segment = 1;

    public void Main(Controller args, Wheels wheels, Shooter shooter){ //each revolution is 6pi inches
        double angleFacing = args.GetAngleFacing();
        double frontLeftRotations = wheels.getRotations("fL");
        double backLeftRotations = wheels.getRotations("bR");
        
    if(frontLeftRotations < 4.17781725616 && backLeftRotations < 4.17781725616 && segment == 1){ //forward
        wheels.drive(.5, .5);
        return;
    }
    if(angleFacing<90 && segment == 1){ //turn right
        wheels.drive(.5, -.5);
        return;
    }
    if (angleFacing >= 90 && segment == 1) { //resetting encoder values and starting segment 2
        wheels.resetRotations();
        segment++;
        return;
    }
    if(frontLeftRotations < 7.51158279746 && backLeftRotations < 7.51158279746 && segment == 2){ //forward
    wheels.drive(.5, .5);
            return;
    }
        
    if(angleFacing>-90){ //turning left
        wheels.drive(-.5, .5);
        return;
    }
    if(angleFacing <= -90 && segment == 2){ //resetting encoder values and starting segment 3 (again, not gonna be used)
        wheels.resetRotations();
        segment++;
        return;
    }
    shooter.charge(0.6);
    shooter.fire();
}
}