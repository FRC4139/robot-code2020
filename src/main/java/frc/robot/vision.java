package frc.robot;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import java.awt.image.BufferedImage;

public class vision{
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        Mat frame = new Mat();

        while(true){
            if (camera.read(frame)){
                Imgcodecs.imwrite("feed.jpg", frame);
                System.out.println("ok");
                
            }
        }
    }
}