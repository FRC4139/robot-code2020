package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ColorWheel
{
    private WPI_TalonSRX fly;
    public ColorSensor cs; 

    public String lastColor = "n/a";
    public String currentColor = "n/a";
    public int segmentsPassed = -1;

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

    public void spinUntilThree(Controller c) {
        
        currentColor = cs.ReturnColor();
        if (currentColor != lastColor && currentColor != "NC") {
            segmentsPassed++;
            lastColor = currentColor;
            SmartDashboard.putNumber("Color segments passed: ", segmentsPassed);
        }

        if (segmentsPassed < 25) {
            flyRun(0.3);
            spinNextFrame = true;
            //SmartDashboard.putBoolean("seg < 25", true);
            c.spunTillThree = false;
        } else {
            // once we have spun 3 times, we need to stop spinning
            flyRun(0);
            // and not spin the next frame
            spinNextFrame = false;
            //SmartDashboard.putBoolean("seg < 25", false);
            //System.out.println("stopped spinning");
            c.spunTillThree = true;
        }


    }

    // the following code does not account for an error in which the FMS wants the current color as the desired color... ?
    public void spinToColor(String desiredColor) {
        currentColor = cs.ReturnColor();
        
        if (currentColor != lastColor  && currentColor != "NC") {
            segmentsPassed++;
            lastColor = currentColor;
            SmartDashboard.putNumber("Color segments passed v2: ", segmentsPassed);
        }

        if (!currentColor.equals(desiredColor)) {
            flyRun(0.3);
            spinNextFrame = true;
            //SmartDashboard.putBoolean("seg < 25v2", true);
            //System.out.println("spinning to desired color");
        } else {
            flyRun(0);
            spinNextFrame = false;
            //System.out.println("stopped spinning v2");
            //SmartDashboard.putBoolean("seg < 25v2", false);
        }
    }

}