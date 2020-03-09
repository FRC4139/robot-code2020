package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter
{
    private WPI_TalonSRX blue;
    private WPI_TalonSRX big;
    

    public Shooter(int p1, int p2)
    {
        blue = new WPI_TalonSRX(p1);
        big = new WPI_TalonSRX(p2);
  
    }

    public void charge(double val)
    {
        big.set(-val);
    }


    public void fire(double val)
    {
        blue.set(-val);
    }
}
