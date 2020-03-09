package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
public class DIO{
    DigitalInput p;
    public DIO (int p){
        this.p = new DigitalInput(p);
        //this.p.close();
    }
    public boolean getDIOstatus(){
        return p.get();
    }
}