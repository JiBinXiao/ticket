package pri.xjb.ticket.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xjb
 * @date: 2019/8/10
 * @description:
 **/
@ApiModel("分页传参对象")
public class PageParam {
    @ApiModelProperty(value = "每页数量", required = true, example = "5")
    private int pageSize;
    @ApiModelProperty(value = "当前页码", required = true, example = "1")
    private int pageNow;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }
}
