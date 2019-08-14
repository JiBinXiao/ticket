package pri.xjb.ticket.model.ticket.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author: xjb
 * @date: 2019/8/10
 * @description:
 **/
@ApiModel(value = "门票更新实体类")
public class TicketUpdateParam {
    @ApiModelProperty(value = "门票id", required = true,example = "1")
    private Integer id;

    @ApiModelProperty(value = "门票分类id", required = true,example = "3")
    private Integer categoryId;
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
    @ApiModelProperty(value = "是否转售,1是 2否", required = true,example = "2")
    private Integer reSeller=2;
    @ApiModelProperty(value = "是否保密个人信息。1是 2否", required = true,example = "1")
    private Integer secrecy=1;
    @ApiModelProperty(value = "是否发布。1是 2否", required = true,example = "1")
    private Integer st=1;

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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
        this.remark = remark;
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
}
