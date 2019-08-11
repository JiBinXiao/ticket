package pri.xjb.ticket.mapper;

import org.apache.ibatis.annotations.Param;
import pri.xjb.ticket.common.request.PageParam;
import pri.xjb.ticket.model.ticketCategory.response.TicketCategory;

import java.util.List;

public interface TicketCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TicketCategory record);

    int insertSelective(TicketCategory record);

    TicketCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketCategory record);

    int updateByPrimaryKey(TicketCategory record);

    List<TicketCategory> queryAll(@Param("pageSize") int pageSize,@Param("startIndex")  int startIndex);

    int queryAllCount();
}