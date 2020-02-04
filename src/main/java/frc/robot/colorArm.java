package frc.robot;

import edu.wpi.first.wpilibj.Servo;
public class colorArm {
    private Servo servo;

    public colorArm(int pwmChannel){
        servo = new Servo(pwmChannel);
    }
    
    public void SetVal(double val){
      servo.set(val);
    }

    public double GetVal() {
      return servo.get();
    }

}