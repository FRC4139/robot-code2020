package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class hookExtension
{
    private WPI_TalonSRX motor;

    //port 1 place holder
    public hookExtension(int port1)
     {
        motor = new WPI_TalonSRX(port1);
    }
    
    //lowers hook
    public void lower()
    {
            motor.set(-.75);
    }

    //raises hook
    public void raise()
    {
        motor.set(.75);
    }
}