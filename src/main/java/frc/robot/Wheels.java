package frc.robot;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import java.lang.Math;

public class Wheels {

    private WPI_TalonSRX frontLeft, backLeft, frontRight, backRight;
    private DifferentialDrive wheels;
    private boolean inverseState;

    public Wheels(int fL, int bL, int fR, int bR) {
        frontLeft = new WPI_TalonSRX(fL);
        backLeft = new WPI_TalonSRX(bL);
        frontRight = new WPI_TalonSRX(fR);
        backRight = new WPI_TalonSRX(bR);
        inverseState = false;
        wheels = new DifferentialDrive(new SpeedControllerGroup(frontLeft, backLeft), new SpeedControllerGroup(frontRight, backRight));
    }

    public int getRotations(String location) { 
        if (location == "fL") {
            int temp = frontLeft.getSelectedSensorPosition();
            return temp;
        }

        return 0;

    }

    // Negative speed turns wheels backwards
    public void drive(double leftSpeed, double rightSpeed) {
        
        if(inverseState)
        {
            //if inversState is true, reverse the speeds and call drive 
            leftSpeed = leftSpeed*-1;
            rightSpeed = rightSpeed*-1;
            wheels.tankDrive(leftSpeed, rightSpeed);
        }
        else
        {
            //makes sure speeds are positive
            wheels.tankDrive(Math.abs(leftSpeed), Math.abs(rightSpeed));
        }
    }
    public void inverse()
    {
        //reverses inverse everytime pressed
        inverseState = !inverseState;
    }
}