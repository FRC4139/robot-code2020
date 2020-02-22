package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ColorWheel
{
    private WPI_TalonSRX fly;
    public ColorSensor cs; 

    public String lastColor = "n/a";
    public String currentColor = "n/a";
    public int segmentsPassed = -1;
    public ColorWheel(int p1)
    {
        fly = new WPI_TalonSRX(p1);
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
        }
        if (segmentsPassed < 25) {
            flyRun(1);
        }
    }
}
