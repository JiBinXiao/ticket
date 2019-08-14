package pri.xjb.ticket.common.utis;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import net.sourceforge.tess4j.util.LoadLibs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author: xjb
 * @date: 2019/8/11
 * @description:
 **/
public class Tess4jUtils {

    private static  ITesseract instance = new Tesseract();//JNA Interface Mapping




    public static String identifyWords(String filePath) throws TesseractException, IOException {
        File imageFile = new File(filePath);
        BufferedImage image = ImageIO.read(imageFile);
        //对图片进行处理
        try {
            image = convertImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        instance.setDatapath("H:\\xjb\\java\\ticket\\src\\main\\resources\\tessdata");
        instance.setLanguage("chi_sim");//使用中文字库
        String result = instance.doOCR(image); //识别

        return result;
    }


    public static void main(String[] args) throws Exception {
        String filePath="H:\\xjb\\java\\ticket\\src\\main\\resources\\testImage\\t6.jpg";
        System.out.println(identifyWords(filePath));
    }

    //对图片进行处理 - 提高识别度
    public static BufferedImage convertImage(BufferedImage image) throws Exception {
        //按指定宽高创建一个图像副本
        //image = ImageHelper.getSubImage(image, 0, 0, image.getWidth(), image.getHeight());
        //图像转换成灰度的简单方法 - 黑白处理
        image = ImageHelper.convertImageToGrayscale(image);
        //图像缩放 - 放大n倍图像
        image = ImageHelper.getScaledInstance(image, image.getWidth() * 3, image.getHeight() * 3);
        return image;
    }
}
