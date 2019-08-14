package pri.xjb.ticket.model.ticket.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pri.xjb.ticket.model.user.response.TicketUserPart;
import pri.xjb.ticket.model.ticketCategory.response.TicketCategory;

import java.math.BigDecimal;

@ApiModel(value = "门票新增实体类")
public class Ticket {
    @ApiModelProperty(value = "门票id", required = true)
    private Integer id;

    @ApiModelProperty(value = "门票所属分类", required = true)
    private TicketCategory ticketCategory;

    @ApiModelProperty(value = "所属人", required = true)
    private TicketUserPart ticketUser;

    @ApiModelProperty(value = "价格", required = true)
    private BigDecimal price;
    @ApiModelProperty(value = "所属通道", required = false)
    private String aisle;
    @ApiModelProperty(value = "所属楼层", required = false)
    private Integer floor;
    @ApiModelProperty(value = "所属行数", required = false)
    private Integer row;
    @ApiModelProperty(value = "所属列数", required = false)
    private Integer column;

    @ApiModelProperty(value = "备注", required = false)
    private String remark;
    @ApiModelProperty(value = "是否转售,1是 2否", required = true, example = "2")
    private Integer reSeller;
    @ApiModelProperty(value = "是否保密个人信息。1是 2否", required = true, example = "1")
    private Integer secrecy;
    @ApiModelProperty(value = "是否关注", required = false, example = "true")
    private boolean isAttention = true;
    @ApiModelProperty(value = "状态 1发布 2未发布", required = false, example = "1")
    private int st = 1;

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

    public TicketUserPart getTicketUser() {
        return ticketUser;
    }

    public void setTicketUser(TicketUserPart ticketUser) {
        this.ticketUser = ticketUser;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public Integer getReSeller() {
        return reSeller;
    }

    public void setReSeller(Integer reSeller) {
        this.reSeller = reSeller;
    }

    public Integer getSecrecy() {
        return secrecy;
    }

    public void setSecrecy(Integer secrecy) {
        this.secrecy = secrecy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketCategory=" + ticketCategory +
                ", ticketUser=" + ticketUser +
                ", price=" + price +
                ", aisle=" + aisle +
                ", floor=" + floor +
                ", row=" + row +
                ", column=" + column +
                ", remark='" + remark + '\'' +
                ", reSeller=" + reSeller +
                ", secrecy=" + secrecy +
                ", isAttention=" + isAttention +
                ", st=" + st +
                '}';
    }
}