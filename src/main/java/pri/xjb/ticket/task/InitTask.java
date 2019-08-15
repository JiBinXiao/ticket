package pri.xjb.ticket.task;

import com.baidu.aip.ocr.AipOcr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pri.xjb.ticket.common.api.BaiduApi;
import pri.xjb.ticket.common.utis.SongNameUtils;
import pri.xjb.ticket.common.utis.Tess4jUtils;

import javax.annotation.PostConstruct;

/**
 * @author: xjb
 * @date: 2019/8/13
 * @description:
 **/
@Component
public class InitTask {
    private static final Logger logger = LoggerFactory.getLogger(InitTask.class);


    //设置APPID/AK/SK
    @Value("${baidu.APP_ID}")
    private String APP_ID;
    @Value("${baidu.API_KEY}")
    private String API_KEY;
    @Value("${baidu.SECRET_KEY}")
    private String SECRET_KEY;

    @PostConstruct
    public void init() {

        SongNameUtils.initName();
        logger.info("初始化五月天歌名完成");
        BaiduApi.aipOcr = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        logger.info("初始化百度图片识别");
    }

}
