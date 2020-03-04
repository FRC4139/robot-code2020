package frc.robot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.videoio.VideoCapture;

public class Vision {

    private int hue;
    private int highH;
    private int sat;
    private int highS;
    private int val;
    private int highV;
    private VideoCapture cap;

    public Vision(int lowHue, int lowSaturation, int lowValue, int hRange, int sRange, int vRange /*int highSaturation, int highValue */) {
        hue = lowHue - hRange;
        highH = lowHue + hRange;
        sat = lowSaturation - sRange;
        highS = lowSaturation + sRange;
        val = lowValue - vRange;
        highV = lowValue + vRange;
        try {
            cap = new VideoCapture(0);
        } catch(UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
        if(!cap.isOpened()) {
            System.out.println("!!!!!!!! VISION ERROR !!!!!!!!");
            System.out.println("Could not open VideoCapture");
        }
    }

    // Returns the direction the robot should go to get itself back on center. Either "left", "right", "center", or "ERROR" if there was an issue with calculations
    public String checkDirection() {
        Mat frame = new Mat();
        cap.read(frame);
        
        Mat resized = new Mat();
        Size sz = new Size(640, 480);
        Imgproc.resize(frame, resized, sz);

        // resized image is now located in the "resized" Mat

        Mat frameHSV = new Mat();
        Imgproc.cvtColor(resized, frameHSV, Imgproc.COLOR_BGR2HSV);
        Mat thresh = new Mat();
        Core.inRange(frameHSV, new Scalar(hue, sat, val), new Scalar(highH, highS, highV), thresh);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(thresh, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        double maxArea = 0;
        MatOfPoint maxContour = new MatOfPoint();

        Iterator<MatOfPoint> iterator = contours.iterator();
        while(iterator.hasNext()) {
            MatOfPoint contour = iterator.next();
            double area = Imgproc.contourArea(contour);
            if(area > maxArea) {
                maxArea = area;
                maxContour = contour;
            }
        }

        // Largest contour is now in the "maxContour" variable
        
        double threshold = 0.1 * sz.width;

        Moments moments = Imgproc.moments(maxContour);
        Point center = new Point();

        center.x = moments.get_m10() / moments.get_m00();

        double frameCenter = sz.width / 2;

        if(center.x > (frameCenter - threshold) && center.x < (frameCenter + threshold)) {
            return "center";
        } else if(center.x < (frameCenter - threshold)) {
            return "right";
        } else if(center.x > (frameCenter + threshold)) {
            return "left";
        } else {
            return "ERROR";
        }
    }
}