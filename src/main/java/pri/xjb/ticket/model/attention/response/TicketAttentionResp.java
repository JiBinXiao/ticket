package pri.xjb.ticket.model.attention.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pri.xjb.ticket.model.ticket.response.Ticket;
import pri.xjb.ticket.model.user.response.TicketUserPart;

import java.util.Date;

/**
 * @author: xjb
 * @date: 2019/8/11
 * @description:
 **/
@ApiModel("我关注的门票")
public class TicketAttentionResp {
    @ApiModelProperty(value = "关注id")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "门票id")
    private Integer tid;
    @ApiModelProperty(value = "门票对象")
    private Ticket ticket;
    @ApiModelProperty(value = "状态")
    private Integer st;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ctime;
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date utime;


    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
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
}
