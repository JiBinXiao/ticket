package pri.xjb.ticket.model.ticket.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author: xjb
 * @date: 2019/8/11
 * @description:
 **/
@ApiModel(value = "门票检索通道号实体类")
public class TicketGetAisleParam {

    @ApiModelProperty(value = "门票分类id", required = true,example = "1")
    private Integer categoryId;

    @ApiModelProperty(value = "单价", required = true,example = "755")
    private BigDecimal price;

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
}
