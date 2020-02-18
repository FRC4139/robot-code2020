package frc.robot;

//import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ColorSensor
{
    private final ColorSensorV3 m_colorSensor;
    private final ColorMatch m_colorMatcher;

    private final Color kBlueTarget = ColorMatch.makeColor(0.1, 0.45, 0.45); //cyan (blue)
    private final Color kGreenTarget = ColorMatch.makeColor(0.15, 0.6, 0.25); //green
    private final Color kRedTarget = ColorMatch.makeColor(0.5, 0.35, 0.15); //red
    private final Color kYellowTarget = ColorMatch.makeColor(0.3, 0.55, 0.15); //yellow 
    public ColorSensor()
    {
        m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        m_colorMatcher = new ColorMatch();
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);    
    }

    public String ReturnColor()
    {
        Color detectedColor = m_colorSensor.getColor();

        /**
         * Run the color match algorithm on our detected color
         */
        String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
        colorString = "Blue";
        } else if (match.color == kRedTarget) {
        colorString = "Red";
        } else if (match.color == kGreenTarget) {
        colorString = "Green";
        } else if (match.color == kYellowTarget) {
        colorString = "Yellow";
        } else {
        colorString = "Unknown";
        }

        /**
         * Open Smart Dashboard or Shuffleboard to see the color detected by the 
         * sensor.
         */
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putString("Detected Color", colorString);
        SmartDashboard.putNumber("Confidence", match.confidence);
        
        return colorString;
    }
}