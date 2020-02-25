package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake {

    private WPI_TalonSRX bagMotor;

    public Intake(int p1){
        bagMotor = new WPI_TalonSRX(p1);

    }

    public void drive(double val1){
        bagMotor.set(val1);
    }

}

