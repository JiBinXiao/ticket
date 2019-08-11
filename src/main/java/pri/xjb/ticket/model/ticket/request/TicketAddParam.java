package pri.xjb.ticket.model.ticket.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xjb
 * @date: 2019/8/10
 * @description:
 **/
@ApiModel("新增门票参数")
public class TicketAddParam {

    @ApiModelProperty(value = "门票分类id", required = true,example = "3")
    private Integer categoryId;
    @ApiModelProperty(value = "价格", required = true)
    private BigDecimal price;
    @ApiModelProperty(value = "所属通道", required = false)
    private Integer aisle;
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

    @ApiModelProperty(hidden = true)
    private Date ctime = new Date();
    @ApiModelProperty(hidden = true)
    private Date utime = new Date();
    @ApiModelProperty(value = "创建人",hidden = true)
    private Integer cuser;
    @ApiModelProperty(value = "修改人",hidden = true)
    private Integer uuser;

    public Integer getCuser() {
        return cuser;
    }

    public Integer getUuser() {
        return uuser;
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

    public void setCuser(Integer cuser) {
        this.cuser = cuser;
    }

    public void setUuser(Integer uuser) {
        this.uuser = uuser;
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

    public Integer getAisle() {
        return aisle;
    }

    public void setAisle(Integer aisle) {
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
}
