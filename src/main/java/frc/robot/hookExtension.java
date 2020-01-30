package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class hookExtension
{
    //lowers hook
    public void lower (WPI_TalonSRX drop)
    {
            drop.set(-.75);
    }

    //raises hook
    public void raise (WPI_TalonSRX lift)
    {
        lift.set(.75);
    }
}