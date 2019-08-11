package pri.xjb.ticket.model.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xjb
 * @date: 2019/8/10
 * @description:
 **/
@ApiModel("用户-更新密码类")
public class TicketUserModifyPwdParam {
    @ApiModelProperty(value = "用户id，前端不用传", required = false)
    private Integer id;
    @ApiModelProperty(value = "原始密码", required = true)
    private String pwd;
    @ApiModelProperty(value = "新密码", required = true)
    private String newPwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
