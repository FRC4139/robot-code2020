package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import java.lang.Math;

public class AutonomousTop{
    public int segment = 0;
    public static final double inchesToHoopLeft = 141.59;
    
    public void hi(Controller contr, Wheels wheels, Shooter shooter){ //each revolution is 6pi inches
        double angleFacing = contr.getAngleFacing();
        double frontLeftRotations = wheels.getRotations("fL");
        double backLeftRotations = wheels.getRotations("bR");
        
        /* LEFT */
        switch(segment){
            case 0:
                wheels.drive(.5, .5);
                segment++;
                break;
            case 1:
                if(wheels.getRotations("fL") >= 1){
                    wheels.drive(-.5, -.5);
                    segment++;
                }
                break;
            case 2:
                if(wheels.getRotations("fL") <= 0){
                    wheels.drive(.5, -.5);
                    segment++;
                }
                break;
            case 3:
                if(contr.getAngleFacing() >= 90){
                    wheels.resetRotations();
                    wheels.drive(.5, .5);
                    segment++;
                }
                break;
            case 4:
                if(wheels.getRotations("fL") >= inchesToHoopLeft/(6*Math.PI)){
                    wheels.drive(-.5, .5);
                    segment++;
                }
                break;
            case 5:
                if(contr.getAngleFacing() <= 0){
                    wheels.drive(0, 0);
                    shooter.charge(.6);
                    shooter.fire();
                }
        }
        while(frontLeftRotations < inchesToHoopLeft/(6*Math.PI)){ //forward
            wheels.drive(.5, .5);
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

