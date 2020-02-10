package frc.robot;

import edu.wpi.first.wpilibj.Servo;
public class DrawBridge {
    private Servo servo;

    public DrawBridge(int pwmChannel){
        servo = new Servo(pwmChannel);
    }
    
    public void SetVal(double val){
      servo.set(val);
    }

    public double GetVal() {
      return servo.get();
    }

}
