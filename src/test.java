import com.sun.jndi.toolkit.url.Uri;
import org.opencv.core.*;
import com.czdpzc.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

public class test {

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
        i1 = imread("D:\\tempMatch\\src\\Sample_czd\\match_area\\4.jpg");
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
        System.out.println("匹配的第一个数字为：" + firstNum);

        targetImageDiv divImg = new targetImageDiv(showImg);
        divImg.getOne();//分割
        for (int j = 1; j <= 4; j++) {
            path3 = path1 + "div_pic\\" + j + ".jpg";
            imgDived = imread(path3);

            selectTemp2Match selectTemp2Match = new selectTemp2Match(imgDived);
            matchedChar[j - 1] = selectTemp2Match.select2match();
        }
        System.out.println("------------------------------");
        System.out.printf(".jpg匹配结果为：");
        for (int w = 0; w < matchedChar.length; w++) {
            System.out.print(matchedChar[w]);
            System.out.print(" ");
        }
        long consumingTime = System.nanoTime()-startTime;
        System.out.println("------------------------------");

        /**
         * 未取消selectTemp2Match匹配数字部分字符！！！注意！！！！
         */

        System.out.println("--------------------------------------");
        System.out.println("                匹配结束：D"+consumingTime/1000000+"ms");
        System.out.println("--------------------------------------");
    }
//    public static void main(String[] args) {
//
//        Mat targetImg = new Mat();
//        Mat imgDived = new Mat();
//        String path1 = ".\\src\\Sample_czd\\";
//        String path2;
//        String path3;
//
//
//        System.out.println("=====================Match Start : )============================");
//        System.out.println("");
//        System.out.println("=====================注意！！！===================================");
//        System.out.println("本程序将把match_area里的图片均识别！！");
//        System.out.println("================================================================");
//
//
//
////
//
//
//            path2 = path1 + "match_area\\" + 7+ ".jpg";
//            char[] matchedChar = new char[4];
//
//            targetImg = imread(path2);
//
//            targetImageDiv divImg = new targetImageDiv(targetImg);
//            divImg.getOne();//分割
//            for (int j = 1;j<=4;j++){
//                path3 = path1 + "div_pic\\" + j + ".jpg";
//                imgDived = imread(path3);
//
//                selectTemp2Match selectTemp2Match = new selectTemp2Match(imgDived);
//                matchedChar[j-1] = selectTemp2Match.select2match();
//            }
//            System.out.println("------------------------------");
//            System.out.printf(".jpg匹配结果为：");
//            for (int w = 0;w<matchedChar.length;w++){
//                System.out.print(matchedChar[w]);
//                System.out.print(" ");
//            }
//            System.out.println("------------------------------");
//
//
//
//
//        System.out.println("--------------------------------------");
//        System.out.println("                匹配结束：D");
//        System.out.println("--------------------------------------");
//
//
//
////        i1 = Imgcodecs.imread("D:\\tempMatch\\src\\Sample_czd\\match_area\\3.jpg");
////        Imgproc.cvtColor(i1,i1_gray,Imgproc.COLOR_RGB2GRAY);
////        Imgproc.threshold(i1_gray,i2,35,255,Imgproc.THRESH_BINARY);
////
////        imwrite("C:\\Users\\2b\\Desktop\\i1_gray.jpg",i1_gray);
////        imwrite("C:\\Users\\2b\\Desktop\\i2.jpg",i2);
//
////        int x = i2.cols();
////        System.out.println(x);
////        double x = getColSum(i2,50);
////        System.out.println(x);
//
//    }


}

