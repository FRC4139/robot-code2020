package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import java.lang.Math;

public class Autonomous{
    
    boolean button3 = false;
    boolean button2 = false;
    boolean button1 = false;

    double encoder = 0;

    public void main(String[] args){ //each revolution is 6pi inches
        if(button3 == true){
            double frontLeftRotations = wheels.getRotations("fL");
            double backLeftRotations = wheels.getRotations("bR");
            //turnLeft();
            while(encoder < /* placeholder for distance from initial position to hoop position */){
                //moveForward();
            }
            //turnRight();
            while(encoder>1){
                //moveForward();
            }
                //turn180;
            while(encoder < 4.45632798574){ //move back 7ft
                //moveBack();
            }
                
            }    
        }
        if(button2==true){
            
            while(encoder < 3){
                //turnRight();
                while(encoder < /* placeholder for distance from initial position to hoop position */){
                    //moveForward();
                }
                //turnLeft();
                while(encoder>1){
                    //moveForward();
                }
                while(encoder < 4.45632798574){ //move back 7ft
                    //moveBack();
                }
            }
        }
        if(button1==true){
            
            while(encoder < 6){
                //turnRight();
                while(encoder < /* placeholder for distance from initial position to hoop position */){
                    //moveForward();
                }
                //turnLeft();
            }
        }
    }

}