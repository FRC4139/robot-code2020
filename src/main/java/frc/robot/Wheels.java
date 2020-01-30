package frc.robot;

//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Wheels {

    private WPI_TalonSRX frontLeft, backLeft, frontRight, backRight;
    // tank drive
    private DifferentialDrive wheels;

    public Wheels(int fL, int bL, int fR, int bR) {
        frontLeft = new WPI_TalonSRX(fL);
        backLeft = new WPI_TalonSRX(bL);
        frontRight = new WPI_TalonSRX(fR);
        backRight = new WPI_TalonSRX(bR);

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        wheels = new DifferentialDrive(new SpeedControllerGroup(frontLeft, backLeft), new SpeedControllerGroup(frontRight, backRight));
    }

    // Negative speed turns wheels backwards
    public void drive(double leftSpeed, double rightSpeed) {
        wheels.tankDrive(leftSpeed, rightSpeed);
    }

    public int getRotations(String location) { 
        if (location == "fL") {
            return frontLeft.getSelectedSensorPosition();
        }
        if (location == "bL") {
            return backLeft.getSelectedSensorPosition();
        }
        if (location == "fR") {
            return frontRight.getSelectedSensorPosition();
        }
        if (location == "bR") {
            return backRight.getSelectedSensorPosition();
        }

        return 404;

    }


}