package com.learn;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class FaceDetector {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread("\"C:\\Users\\gokul\\OneDrive\\Pictures\\Screenshots\\Screenshot 2023-10-19 143621.png\");

        // create method for detect and save
        detectAndSave(image);



    }

    private static void detectAndSave(Mat image) {
        // create some objects
        MatOfRect faces = new MatOfRect(); // store more than one face

        // convert to gray scale
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(image, grayFrame, Imgproc.COLOR_BGR2GRAY);

        // improve contrast for better result
        Imgproc.equalizeHist(grayFrame, grayFrame);

        int height = grayFrame.height();
        int absoluteFaceSize = Math.max(Math.round(height * 0.2f), 0);

        // detect faces
        CascadeClassifier faceCascade = new CascadeClassifier();
        // load trained data file
        faceCascade.load("C:\\Users\\gokul\\Downloads\\opencv\\build\\java\\x64\\haarcascade_frontalface_alt2.xml"); // Correct the path
        faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, Objdetect.CASCADE_SCALE_IMAGE,
                new Size(absoluteFaceSize, absoluteFaceSize), new Size());

        // write to file
        Rect[] faceArray = faces.toArray();
        for (Rect rect : faceArray) {
            // draw rect
            Imgproc.rectangle(image, rect, new Scalar(0, 0, 255), 5);
        }

        Imgcodecs.imwrite("images/output.jpg", image); // Correct the path
        System.out.println("Write success: " + faceArray.length);
    }
}

