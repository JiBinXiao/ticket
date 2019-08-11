package pri.xjb.ticket.mapper;

import org.apache.ibatis.annotations.Param;
import pri.xjb.ticket.model.ticket.request.*;
import pri.xjb.ticket.model.ticket.response.Ticket;

import java.util.List;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);



    Ticket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ticket record);


    List<Ticket> queryAll(TicketQueryParam ticketRequestParam);

    Integer queryAllCount(TicketQueryParam ticketRequestParam);

    int addByTicketAddParam(TicketAddParam ticketAddParam);


    int cancelByd(@Param("userId") int userId,@Param("id") Integer id);

    int releaseById(@Param("userId") int userId,@Param("id") Integer id);

    int updateByTicketUpdateParam(TicketUpdateParam ticketUpdateParam);

    int queryMyAddCount(int userId);

    List<Ticket> queryMyAdd(@Param("userId")int userId,
                            @Param("startIndex") int startIndex,
                            @Param("pageSize")int pageSize);

    List<String> getAisleByPrice(TicketGetAisleParam ticketGetAsileParam);

    List<String> getRowNumByPriceAndAisle(TicketGetRowNumParam ticketGetRowNumParam);
}