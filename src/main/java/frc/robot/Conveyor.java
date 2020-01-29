package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; // always import this

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class Conveyor {
    private WPI_TalonSRX motor;
    private WPI_TalonSRX cimMotor;
    private SpeedController speedController;

    public Conveyor(int p1, int p2){
        motor = new WPI_TalonSRX(p1);
        cimMotor = new WPI_TalonSRX(p2);
        speedController = new SpeedControllerGroup(motor, motor);
    }
    
    public void drive(double val1, double val2){
      
    }

    
}