package pri.xjb.ticket.model.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: xjb
 * @date: 2019/8/9
 * @description:
 **/
@ApiModel("用户-更新实体类")
public class TicketUserUpdateParam {
    @ApiModelProperty(value = "用户id,前端不用传", required = false)
    private Integer id;
    @ApiModelProperty(value = "昵称", required = false)
    private String username;
    @ApiModelProperty(value = "性别 0:保密 1:男 2:女", required = true)
    private Integer sex;

    @ApiModelProperty(value = "微信号", required = false)
    private String wxnum;


    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date utime = new Date();
    @ApiModelProperty(value = "简介", required = false)
    private String intro;

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
}
