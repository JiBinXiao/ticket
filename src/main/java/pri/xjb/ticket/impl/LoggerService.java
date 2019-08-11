package pri.xjb.ticket.impl;


import pri.xjb.ticket.mapper.LoggerEntityMapper;
import pri.xjb.ticket.model.LoggerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xjb on 2019/1/14
 **/
@Service
public class LoggerService {
    @Autowired
    LoggerEntityMapper loggerEntityMapper;

    public int insertSelective(LoggerEntity loggerEntity) {
        return loggerEntityMapper.insertSelective(loggerEntity);
    }

    /**
     * 每日访问次数
     * @param username
     * @param date
     * @return
     */
    public int getCountByUsername(String username, String date) {
        return loggerEntityMapper.getCountByUsername(username, date);
    }
}
