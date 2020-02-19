package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter
{
    private WPI_TalonSRX shoot1;
    private WPI_TalonSRX shoot2;

    public Shooter(int p1, int p2)
    {
        shoot1 = new WPI_TalonSRX(p1);
        shoot2 = new WPI_TalonSRX(p2);
    }

    public void shootRun(double val)
    {
        shoot1.set(val);
    }

    public void loader()
    {
        shoot2.set(0.1);
    }
}