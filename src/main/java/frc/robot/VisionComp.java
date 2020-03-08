package frc.robot;

public class VisionComp
{
    private int signal;
    private Wheels controll;

    public VisionComp(int input)
    {
        signal = input;
        // 1 2 3 4 are place holders for tallon ports
        controll = new Wheels(1,2,3,4);
    }
    
    public void check()
    {
        if(signal == 1)
        {
          //turn right
          controll.drive(1,-1);
        }

        if(signal == 0)
        {
            //turn left
            controll.drive(-1,1);
        }
    }
}