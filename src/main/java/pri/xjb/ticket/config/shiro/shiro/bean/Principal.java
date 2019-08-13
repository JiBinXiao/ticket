package pri.xjb.ticket.config.shiro.shiro.bean;



import pri.xjb.ticket.common.utis.UserUtils;
import pri.xjb.ticket.model.user.response.TicketUser;
import pri.xjb.ticket.model.user.response.TicketUserPart;

import java.io.Serializable;

/**
 * 授权用户 信息类
 * Created by xjb on 2018/11/29
 **/
public class Principal implements Serializable {

    private static final long serialVersionUID =-5109658133297123281L;
    private Integer id;

    private String username;
    private String phone;



    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Principal() {
    }

    public Principal(TicketUser user) {
        this.id = user.getId();
        this.phone=user.getPhone();
        this.username=user.getUsername();

    }

    /**
     * 获取SESSIONID
     */
//    public String getSessionid() {
//        try {
//            return (String) UserUtils.getSession().getId();
//        } catch (Exception e) {
//            return "";
//        }
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
