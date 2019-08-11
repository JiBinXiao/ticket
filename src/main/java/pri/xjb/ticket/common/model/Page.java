package pri.xjb.ticket.common.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * Created by xjb on 2018/9/25
 **/
@ApiModel(value = "统一翻页对象")
public class Page<T> implements Serializable {

    private final long serializableId = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页码")
    private int pageNow = 1;
    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量")
    private int pageSize = 10;
    /**
     * 每页最大翻页数量
     */

    private int limit = 50;
    /**
     * 开始序号 方便分页查询
     */
    @ApiModelProperty(value = "开始下标")
    private int startIndex = 0;
    /**
     * 结束序号  方便分页查询
     */
    @ApiModelProperty(value = "结束下标")
    private int endIndex = 0;
    /**
     * 页码总数
     */
    @ApiModelProperty(value = "页码总数")
    private int totalPage = 0;
    /**
     * 数据总数
     */
    @ApiModelProperty(value = "数据总数")
    private int totalCount = 0;

    /**
     * 分页查询集合
     */

    private List<T> list = new ArrayList<>();

    public Page() {
    }

    public Page(int pageNow, int pageSize, int totalCount) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.setPage();
    }

    //设置分页的属性
    public void setPage() {
        int pageNow = this.getPageNow();
        int pageSize = this.getPageSize();
        int totalCount = this.getTotalCount();
        //检查pageSize
        if (pageSize <= 0) {
            pageSize = 5;
            this.pageSize = pageSize;
        }

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        int startIndex = (pageNow - 1) * pageSize;
        //避免下标越界
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (startIndex > totalCount) {
            startIndex = totalCount;
        }


        int endIndex = startIndex + pageSize;

        //检查下标越界
        if (endIndex > totalCount) {
            endIndex = totalCount;
        }
        this.setEndIndex(endIndex);
        this.setTotalPage(totalPage);
        this.setStartIndex(startIndex);

    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        if (pageSize >= limit)
            pageSize = limit;
        if (pageSize <= 0)
            pageSize = 5;
        this.pageSize = pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
