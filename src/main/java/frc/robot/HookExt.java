package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Lifter {
    private WPI_TalonSRX motor1;
    private WPI_TalonSRX motor2;

    public Lifter(int p1, int p2){
        motor1 = new WPI_TalonSRX(p1);
        motor2 = new WPI_TalonSRX(p2);

    }

    public void lift(double val1){
        motor1.set(val1);
        motor2.set(val1);
    }

}