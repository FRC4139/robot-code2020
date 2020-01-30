package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake {
    private WPI_TalonSRX motor;

    public Intake(int port1) {
        motor = new WPI_TalonSRX(port1)
    }

    public void intakeRun(double speed) {
        motor.set(speed);
    }
}

