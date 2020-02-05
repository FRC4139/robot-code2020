package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter
{
    private WPI_TalonSRX shoot;

    public Shooter(int p1)
    {
        shoot = new WPI_TalonSRX(p1);
    }

    public void shootRun(double val)
    {
        shoot.set(val);
    }
}