package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class flyWheel
{
    private WPI_TalonSRX fly;

    public flyWheel(int p1)
    {
        fly = new WPI_TalonSRX(p1);
    }

    public void flyRun(double val)
    {
        fly.set(val);
    }
}