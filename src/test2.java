import com.czdpzc.firstGPS;
import com.czdpzc.selectTemp2Match;
import com.czdpzc.targetImageDiv;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

public class test2 {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    public static void main(String[] args) {
        Mat i1 = new Mat();
        Mat imgDived = new Mat();
        String path1 = ".\\src\\Sample_czd\\";
        String path2;
        String path3;
        char[] matchedChar = new char[4];
        i1 = imread("D:\\tempMatch\\src\\Sample_czd\\match_area\\1.jpg");
        Point bestLoc = new Point();
        char firstNum;

        long startTime = System.nanoTime();

        firstGPS fg = new firstGPS(i1);
        firstNum = fg.select2match();
        bestLoc = fg.getBestLoc();

        Mat showImg = new Mat();
        showImg = fg.firstGPSCut(bestLoc);

        System.out.println(showImg.channels());

//        finalImg.copyTo(showImg);

        imwrite("C:\\Users\\2b\\Desktop\\gpsTest.jpg", showImg);
        Mat threshImg = new Mat();
        Mat canImg = new Mat();
        Mat diImg = new Mat();
        Mat erImg = new Mat();
        Mat finalImg = new Mat();
//        Imgproc.cvtColor(showImg,showImg,Imgproc.COLOR_RGB2GRAY);
        Imgproc.threshold(showImg,showImg,35,255,Imgproc.THRESH_BINARY);
        Imgproc.Canny(showImg,canImg,30,200);
        imwrite("C:\\Users\\2b\\Desktop\\cannyTest.jpg", canImg);
        Imgproc.dilate(canImg,diImg,new Mat());
        imwrite("C:\\Users\\2b\\Desktop\\diTest.jpg", diImg);
        Imgproc.erode(canImg,erImg,new Mat());
        imwrite("C:\\Users\\2b\\Desktop\\erTest.jpg", erImg);

        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,new Size(1,1));

        Imgproc.morphologyEx(canImg,finalImg,Imgproc.MORPH_DILATE,element);
        imwrite("C:\\Users\\2b\\Desktop\\fiTest.jpg", finalImg);


    }
}
