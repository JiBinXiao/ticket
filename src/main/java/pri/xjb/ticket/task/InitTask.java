package pri.xjb.ticket.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pri.xjb.ticket.common.utis.SongNameUtils;

import javax.annotation.PostConstruct;

/**
 * @author: xjb
 * @date: 2019/8/13
 * @description:
 **/
@Component
public class InitTask {
    private static final Logger logger = LoggerFactory.getLogger(InitTask.class);
    @PostConstruct
    public void init(){
        logger.info("开始初始化五月天歌名");
        SongNameUtils.initName();
    }

}
