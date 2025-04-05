package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
public class Detectare_sample extends SampleDetection {
    HardwareMap hardwareMap;
    SampleDetection sample = new SampleDetection();
    OpenCvInternalCamera camera;
    String webcamName = "Webcam 1";

    public Detectare_sample(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public void init(){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        sample = new SampleDetection();
        camera.setPipeline(sample);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(1280,720, OpenCvCameraRotation.UPRIGHT);

            }

            @Override
            public void onError(int errorCode) {}


        });
    }

    public void stopStreaming(){
        camera.stopStreaming();
    }

    public void startFlashlight() {

        camera.setFlashlightEnabled(true);

    }

    public void stopFlashlight() {

        camera.setFlashlightEnabled(false);

    }


}