package pri.xjb.ticket.model.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xjb
 * @date: 2019/8/9
 * @description:
 **/
@ApiModel("/用户-登录实体类")
public class TicketUserLoginParam {
    @ApiModelProperty(value = "手机号", required = true,example = "15600693173")
    private String phone;
    @ApiModelProperty(value = "密码", required = true,example = "123456")
    private String password;

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
}
