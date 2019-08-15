package pri.xjb.ticket.common.api;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: xjb
 * @date: 2019/8/15
 * @description: 百度图片识别  50000次/天免费   文档：http://ai.baidu.com/docs#/ImageClassify-Java-SDK/top
 **/
@Component
public class BaiduApi {


    public static AipOcr aipOcr;


    public static void main(String[] args) {
      

    }


    /**
     * 图片文字识别
     *
     * @param filePath
     * @return
     */
    public static String recognizeImage(String filePath) {

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        String image = filePath;
        JSONObject res = aipOcr.basicGeneral(image, options);
//        System.out.println(res.toString(2));
        //解析出识别文字
        JSONArray jsonArray = (JSONArray) res.get("words_result");
        StringBuffer stringBuffer = new StringBuffer();
        jsonArray.forEach(item -> {
            JSONObject map = (JSONObject) item;
            String words = (String) map.get("words");
            stringBuffer.append(words);
        });
        return stringBuffer.toString();

    }
}
