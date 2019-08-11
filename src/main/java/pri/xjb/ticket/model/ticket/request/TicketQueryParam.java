package pri.xjb.ticket.model.ticket.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author: xjb
 * @date: 2019/8/8
 * @description:
 **/
@ApiModel(value = "门票检索实体类")
public class TicketQueryParam {

    @ApiModelProperty(value = "门票分类id", required = true,example = "1")
    private Integer categoryId;
    @ApiModelProperty(value = "单价", required = false,example = "755")
    private BigDecimal price;
    @ApiModelProperty(value = "所属通道", required = false)
    private Integer aisle;
    @ApiModelProperty(value = "所属楼层", required = false)
    private Integer floor;
    @ApiModelProperty(value = "所属行号(排数)", required = false)
    private Integer row;
    @ApiModelProperty(value = "所属列号", required = false)
    private Integer column;

    /**
     * 排序列
     */
    @ApiModelProperty(value = "排序字段", required = false)
    private String sortColumn;
    /**
     * 排序方式
     */
    @ApiModelProperty(value = "排序方式", required = false)
    private String sortType;
    @ApiModelProperty(value = "每页数量", required = false)
    private Integer pageSize = 5;
    @ApiModelProperty(value = "当前页码", required = false, example = "1")
    private Integer pageNow = 1;
    @ApiModelProperty(value = "开始下标", hidden = true)
    private Integer startIndex = 0;

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
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

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
