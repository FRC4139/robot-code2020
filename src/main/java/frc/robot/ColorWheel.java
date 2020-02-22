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

    public void spinUntilThree() {
        currentColor = cs.ReturnColor();
        if (currentColor != lastColor) {
            segmentsPassed++;
            lastColor = currentColor;
            SmartDashboard.putNumber("Color segments passed: ", segmentsPassed);
        }

        if (segmentsPassed < 25) {
            flyRun(1);
            spinNextFrame = true;
        } else {
            // once we have spun 3 times, we need to stop spinning
            flyRun(0);
            // and not spin the next frame
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
            flyRun(1);
            spinNextFrame = true;
        } else {
            flyRun(0);
            spinNextFrame = false;
        }
    }

}
