package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
public class ColorWheel
{
    private WPI_TalonSRX fly;
    public ColorSensor cs; 
    private Timer time;
    public String lastColor = "n/a";
    public String currentColor = "n/a";
    public int segmentsPassed = 0; //Changed this to zero

    public Boolean spinNextFrame = false; 
    
    public ColorWheel(int p1, ColorSensor c)
    {
        fly = new WPI_TalonSRX(p1);
        cs = c;
    }

    public void flyRun(double val)
    {
        fly.set(val);
    }


    public void spinNumRotations(int rotations) {
        int segmentsNeeded = rotations*9; // Reason why this is multiplied by nine is because it rotates all the way to the original position, DM me if you need clarification
        currentColor = cs.ReturnColor();
        if (currentColor != lastColor) {
            segmentsPassed++;
            lastColor = currentColor;
            SmartDashboard.putNumber("Color segments passed: ", segmentsPassed);
               
        }
        if (segmentsPassed != segmentsNeeded){
            flyRun(.3);
            spinNextFrame = true;
        }
        else{
            flyRun(0);
            spinNextFrame = false;
        }
      
    }

    // the following code does not account for an error in which the FMS wants the current color as the desired color... ?
    public void spinToColor(String desiredColor) {
        currentColor = cs.ReturnColor();
        if (currentColor != lastColor) {
            segmentsPassed++;
            lastColor = currentColor;
            SmartDashboard.putNumber("Color segments passed: ", segmentsPassed);
        }

        if (currentColor != desiredColor) {
            flyRun(.3);
            spinNextFrame = true;
        } else {
            flyRun(0);
            spinNextFrame = false;
        }
    }

}
