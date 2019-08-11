package pri.xjb.ticket.model.user.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 用户 model
 */
@ApiModel("用户返回结果")
public class TicketUserPart {

    @ApiModelProperty(value = "用户id", required = true)
    private Integer id;
    @ApiModelProperty(value = "手机号", required = false)
    private String phone;

    @ApiModelProperty(value = "昵称", required = false)
    private String username;
    @ApiModelProperty(value = "性别 0:保密 1:男 2:女", required = true)
    private Integer sex;

    @ApiModelProperty(value = "微信号", required = false)
    private String wxnum;


    @ApiModelProperty(value = "简介", required = false)
    private String intro;


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
        this.phone = phone == null ? null : phone.trim();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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
        this.wxnum = wxnum == null ? null : wxnum.trim();
    }




    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

}