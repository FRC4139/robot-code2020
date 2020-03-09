package frc.robot;

public class VisionComp
{
    //private int signal;
    private Wheels controll;

    public VisionComp()
    {
        //signal = input;
        // 1 2 3 4 are place holders for tallon ports
        controll = new Wheels(6,8,3,1);
    }
    
    public void compute(int signal)
    {
        if (signal == 2) {
            controll.drive(0,0);
        }

        if (signal == 2 ) {
            controll.drive(0.7, 0.7);
        }

        if(signal == 1)
        {
          //turn right
          controll.drive(.7,-.7);
        }

        if(signal == 0)
        {
            //turn left
            controll.drive(-.7,.7);
        }
    }
}