package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import java.lang.Math;

public class Autonomous{


    public String position;
    public int segment = 1;

    public void Main(Controller args, Wheels wheels){ //each revolution is 6pi inches
        double angleFacing = args.GetAngleFacing();
        double frontLeftRotations = wheels.getRotations("fL");
        double backLeftRotations = wheels.getRotations("bR");
        if(position=="left"){ //left
            if(frontLeftRotations < 0.4914 && backLeftRotations < 0.4914 && segment == 1){
                wheels.drive(.5, .5);
                return;
            }
            if(angleFacing>-90 && segment == 1){ //MAKE SURE TO SWITCH ALL ANGLES TO NEGATIVE VALUES APPROPRIATELY!!!!!!!!!!!!!!!!!
                wheels.drive(.5, -.5);
                return;
            }
            if (angleFacing >= 90 && segment == 1) {
                wheels.resetRotations();
                segment++;
                return;
            }
            if(frontLeftRotations < /* placeholder */ && backLeftRotations < /* placeholder */ && segment == 2){
                wheels.drive(.5, .5);
                return;
            }
            
            if(angleFacing<90){ //turn 180
                wheels.drive(-.5, .5);
                return;
            }
            if(angleFacing >=90 && segment == 2){
                wheels.resetRotations();
                segment++;
                return;
            }
            shooter.rev();
            shooter.fire();
            }
        
        if(position == "mid"){ //mid
            if(frontLeftRotations < /* placeholder */ && backLeftRotations < /* placeholder */ && segment == 1){
                wheels.drive(.5, .5);
                return;
            }
            if(angleFacing<90 && segment == 1){
                wheels.drive(.5, -.5);
                return;
            }
            if (angleFacing >= 90 && segment == 1) {
                wheels.resetRotations();
                segment++;
                return;
            }
            if(frontLeftRotations < /* placeholder */ && backLeftRotations < /* placeholder */ && segment == 2){
                wheels.drive(.5, .5);
                return;
            }
            
            if(angleFacing<90){ //turn 180
                wheels.drive(-.5, .5);
                return;
            }
            if(angleFacing >=90 && segment == 2){
                wheels.resetRotations();
                segment++;
                return;
            }
            shooter.rev();
            shooter.fire();
            }
        if(position == "right"){ //right
            
            if(frontLeftRotations < /* placeholder */ && backLeftRotations < /* placeholder */ && segment == 1){
                wheels.drive(.5, .5);
                return;
            }
            if(angleFacing<90 && segment == 1){
                wheels.drive(-.5, .5);
                return;
            }
            if (angleFacing >= 90 && segment == 1) {
                wheels.resetRotations();
                segment++;
                return;
            }
            if(frontLeftRotations < /* placeholder */ && backLeftRotations < /* placeholder */ && segment == 2){
                wheels.drive(.5, .5);
                return;
            }
            
            if(angleFacing<90){ //turn 180
                wheels.drive(.5, -.5);
                return;
            }
            if(angleFacing >=90 && segment == 2){
                wheels.resetRotations();
                segment++;
                return;
            }
            shooter.rev();
            shooter.fire();
            }
        }
    }

}