package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; // always import this

public class Conveyor {
    private WPI_TalonSRX motor;

    public Conveyor(int p1, int p2){
        motor = new WPI_TalonSRX(p1);
    }
    
    public void drive(double val1){
      motor.set(val1);
    }


}