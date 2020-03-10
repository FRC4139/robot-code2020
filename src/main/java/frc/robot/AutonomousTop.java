package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import java.lang.Math;

public class AutonomousTop{
    public int segment = 1;

    public void Main(Controller args, Wheels wheels, Shooter shooter){ //each revolution is 6pi inches
        double angleFacing = args.GetAngleFacing();
        double frontLeftRotations = wheels.getRotations("fL");
        double backLeftRotations = wheels.getRotations("bR");
        
        /* LEFT */
            
        if(frontLeftRotations < 1 && backLeftRotations < 1 && segment == 1){ //forward
            wheels.drive(.5, .5);
        }
        if(frontLeftRotations > -1 && backLeftRotations > -1 && segment == 1){ //forward
            wheels.drive(-.5, -.5);
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
        
        if(angleFacing>-90){ // turning left
            wheels.drive(-.5, .5);
            return;
        }
        if(angleFacing <= -90 && segment == 2){ //reset encoder values and starting segment 3 (not gonna be used though)
            wheels.resetRotations();
            segment++;
            return;
        }
        shooter.charge(0.6);
        shooter.fire();
        
        /* MID */
            if(frontLeftRotations < 1 && backLeftRotations < 1 && segment == 1){ //forward
                wheels.drive(.5, .5);
                return;
            }
            if(frontLeftRotations > -1 && backLeftRotations > -1 && segment == 1){ //forward
                wheels.drive(-.5, -.5);
                return;
            }
            if (angleFacing >= 90 && segment == 1) { //resetting encoder values and starting segment 2
                wheels.resetRotations();
                segment++;
                return;
            }
            if(frontLeftRotations < 3.3337655413 && backLeftRotations < 3.3337655413 && segment == 2){ //forward
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
            
        /* RIGHT */
            
            if(frontLeftRotations < 1 && backLeftRotations < 1 && segment == 1){ //forward
                wheels.drive(.5, .5);
                return;
            }
            if(frontLeftRotations > -1 && backLeftRotations > -1 && segment == 1){ //forward
                wheels.drive(-.5, -.5);
                return;
            }
            if(angleFacing<90 && segment == 1){ //turn left
                wheels.drive(-.5, .5);
                return;
            }
            if (angleFacing >= 90 && segment == 1) { //resetting encoder values and starting segment 2
                wheels.resetRotations();
                segment++;
                return;
            }
            if(frontLeftRotations < 0.84405171486 && backLeftRotations < 0.84405171486 && segment == 2){ //forward
                wheels.drive(.5, .5);
                return;
            }
            
            if(angleFacing>-
            90){ //turn right
                wheels.drive(.5, -.5);
                return;
            }
            if(angleFacing <= 90 && segment == 2){ //resetting encoder values and startin encoder 3 (again x2, not using seg 3)
                wheels.resetRotations();
                segment++;
                return;
            }
            shooter.charge(0.6);
            shooter.fire();
        
    }
}

