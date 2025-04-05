package org.firstinspires.ftc.teamcode.Vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class SampleDetection extends OpenCvPipeline {
    Mat imagine = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, imagine, Imgproc.COLOR_RGB2HSV);

        Scalar lowHSV_red = new Scalar(0, 50, 50); //0, 35, 50
        Scalar highHSV_red = new Scalar(10, 255, 255); //30, 255, 255

        Core.inRange(imagine, lowHSV_red, highHSV_red, imagine);

        return null;
    }
}
