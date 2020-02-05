package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ColorWheel
{
    private WPI_TalonSRX fly;

    public ColorWheel(int p1)
    {
        fly = new WPI_TalonSRX(p1);
    }

    public void flyRun(double val)
    {
        fly.set(val);
    }
}