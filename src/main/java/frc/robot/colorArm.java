package frc.robot;

import edu.wpi.first.wpilibj.Servo;

public class ColorArm 
{
    private Servo servo;
    //constructor 
    public ColorArm(int pwmChannel)
    {
        servo = new Servo(pwmChannel);
    }
    
    public void SetVal(double val){
      servo.set(val);
    }

    public double GetVal() {
      return servo.get();
    }

}