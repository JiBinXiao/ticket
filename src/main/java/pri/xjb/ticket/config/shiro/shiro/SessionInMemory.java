package pri.xjb.ticket.config.shiro.shiro;

import org.apache.shiro.session.Session;

import java.util.Date;

/**
 * @author: wangsaichao
 * https://blog.csdn.net/qq_34021712/article/details/80791339
 * Created by xjb on 2018/11/29
 **/
public class SessionInMemory {
    private Session session;
    private Date createTime;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
