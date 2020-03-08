package frc.robot;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
//import java.lang.Math;

public class Wheels {

    private WPI_TalonSRX frontLeft, backLeft, frontRight, backRight;
    //private DifferentialDrive wheels;
    private boolean inverseState;

    public Wheels(int fL, int bL, int fR, int bR) {
        frontLeft = new WPI_TalonSRX(fL);
        backLeft = new WPI_TalonSRX(bL);
        frontRight = new WPI_TalonSRX(fR);
        backRight = new WPI_TalonSRX(bR);
       
        inverseState = false;
        //frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        //backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        //wheels = new DifferentialDrive(new SpeedControllerGroup(frontLeft, backLeft), new SpeedControllerGroup(frontRight, backRight));
    }

    public double getRotations(String location) { 
        if (location == "fL") {
            int temp = frontLeft.getSelectedSensorPosition();
            double val = temp / 4096;
            return val;
        }
        if (location == "bR") {
            int temp = backRight.getSelectedSensorPosition();
            double val = temp / 4096;
            return val;
            
        }
        return 0;

    }

    // Negative speed turns wheels backwards
    public void drive(double leftSpeed, double rightSpeed) {
        
      

        if(inverseState)
        {
            //if inversState is true, reverse the speeds and call drive 
            frontLeft.set(leftSpeed);
            backLeft.set(leftSpeed);
            frontRight.set(-rightSpeed);
            backRight.set(-rightSpeed);
       
            //wheels.tankDrive(leftSpeed, rightSpeed);
        }
        else
        {
            //makes sure speeds are positive
            //wheels.tankDrive(Math.abs(leftSpeed), Math.abs(rightSpeed));
            frontLeft.set(-leftSpeed);
            backLeft.set(-leftSpeed);
            
            frontRight.set(rightSpeed);
            backRight.set(rightSpeed);
       
        }
    }
    public void inverse()
    {
        //reverses inverse everytime pressed
        inverseState = !inverseState;
    }
}