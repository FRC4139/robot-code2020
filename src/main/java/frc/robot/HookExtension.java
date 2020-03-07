package frc.robot;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class HookExtension
{
    private WPI_VictorSPX motor1;
    private WPI_VictorSPX motor2;
    //port 1 place holder
    public HookExtension(int port1, int port2)
     {
        motor1 = new WPI_VictorSPX(port1);
        motor2 = new WPI_VictorSPX(port2);
    }
    
    //lowers hook
    public void lower()
    {
        motor1.set(-.75);
        motor2.set(-.75);
    }

    //raises hook
    public void raise()
    {
        motor1.set(.75);
    }

    public void stop() {
        motor1.set(0);
        motor2.set(0);
    }

}
