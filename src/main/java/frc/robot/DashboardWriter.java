package frc.robot;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
//import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.*;



public class DashboardWriter {

    private ShuffleboardTab selectedTab; 
    //private NetworkTableEntry ultraSonicReading;
    private NetworkTableEntry colorReading;
    private NetworkTableEntry gyroReading;
    private Controller masterController;

    public DashboardWriter(Controller c) {
        selectedTab = Shuffleboard.getTab("User Interface");
        Shuffleboard.selectTab("User Interface");
        colorReading = selectedTab.add("Color", c.GetColorSensing()).getEntry();
        //ultraSonicReading = selectedTab.add("Ultrasonic Reading", c.getUltraSonicReading()).getEntry();
        gyroReading = selectedTab.add("Gyro Val", c.GetAngleFacing())
        .withWidget(BuiltInWidgets.kGyro)
        .withProperties(Map.of("Major tick spacing", 30))
        .getEntry();
        
        
        masterController = c;
    }

    public void AddData(Controller c) {
        colorReading = selectedTab.add("Color", c.GetColorSensing()).getEntry();
        //ultraSonicReading = selectedTab.add("Ultrasonic Reading", c.getUltraSonicReading()).getEntry();
        gyroReading = selectedTab.add("Gyro Val", c.GetAngleFacing())
        .withWidget(BuiltInWidgets.kGyro)
        .withProperties(Map.of("Major tick spacing", 30))
        .getEntry();
    }

    public void Update() {
        colorReading.setString(masterController.GetColorSensing());
        gyroReading.setValue(masterController.ahrs);
    }
    

}