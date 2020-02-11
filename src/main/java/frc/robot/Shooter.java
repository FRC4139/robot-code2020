package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter
{
    private WPI_TalonSRX shoot1;
    private WPI_TalonSRX shoot2;
    private WPI_TalonSRX shoot3;

    public Shooter(int p1, int p2, int p3)
    {
        shoot1 = new WPI_TalonSRX(p1);
        shoot2 = new WPI_TalonSRX(p2);
        shoot3 = new WPI_TalonSRX(p3);
    }

    public void shootRun(double val)
    {
        shoot1.set(val);
        shoot2.set(-val);
    }

    public void loader()
    {
        shoot3.set(0.1);
    }
}
