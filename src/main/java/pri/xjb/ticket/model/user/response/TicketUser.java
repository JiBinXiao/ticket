package pri.xjb.ticket.model.user.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: xjb
 * @date: 2019/8/10
 * @description:
 **/
@ApiModel("用户全字段返回结果")
public class TicketUser {


    @ApiModelProperty(value = "用户id", required = true)
    private Integer id;
    @ApiModelProperty(value = "手机号", required = false)
    private String phone;
    @ApiModelProperty(value = "密码", required = false)
    private String password;
    @ApiModelProperty(value = "昵称", required = false)
    private String username;
    @ApiModelProperty(value = "性别 0:保密 1:男 2:女", required = true)
    private Integer sex;

    @ApiModelProperty(value = "微信号", required = false)
    private String wxnum;

    private Date ltime;


    private Date ctime;

    private Date utime;

    private String intro;

    private Integer st;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getWxnum() {
        return wxnum;
    }

    public void setWxnum(String wxnum) {
        this.wxnum = wxnum;
    }

    public Date getLtime() {
        return ltime;
    }

    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }
}
