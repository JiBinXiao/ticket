package pri.xjb.ticket.mapper;

import org.apache.ibatis.annotations.Param;
import pri.xjb.ticket.model.attention.TicketAttention;
import pri.xjb.ticket.model.attention.response.TicketAttentionResp;

import java.util.Date;
import java.util.List;

public interface TicketAttentionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TicketAttention record);

    int insertSelective(TicketAttention record);

    TicketAttention selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketAttention record);

    int updateByPrimaryKey(TicketAttention record);

    int queryAllCount(int userId);

    List<TicketAttentionResp> queryAll(@Param("userId") int userId,
                                       @Param("startIndex")int startIndex,
                                       @Param("pageSize")int pageSize);

    int add(@Param("userId") Integer userId,
            @Param("tid") Integer tid,
            @Param("ctime") Date ctime);

    int deleteByTid(@Param("userId")int userId, @Param("tid")  Integer tid);

    TicketAttention selectByUserIdAndTid(@Param("userId")int userId,@Param("tid") Integer tid);
}