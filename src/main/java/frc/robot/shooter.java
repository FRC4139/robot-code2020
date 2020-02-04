package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class shooter
{
    private WPI_TalonSRX shoot;

    public shooter(int p1)
    {
        shoot = new WPI_TalonSRX(p1);
    }

    public void shootRun(double val)
    {
        shoot.set(val);
    }
}