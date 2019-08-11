package pri.xjb.ticket.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 统计在线用户的实体类
 * Created by xjb on 2018/12/29
 **/
public class OnlineUser {

    private int id;
    private String username;
    private String nickname;
    private String host;
    private Date ltime;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getLtime() {
        return ltime;
    }

    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }


}
