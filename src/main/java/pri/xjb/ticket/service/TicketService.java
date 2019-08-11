package pri.xjb.ticket.service;

import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.common.request.PageParam;
import pri.xjb.ticket.model.ticket.request.*;
import pri.xjb.ticket.model.ticket.response.Ticket;

import java.util.List;

/**
 * @author: xjb
 * @date: 2019/8/8
 * @description:
 **/
public interface TicketService {
    Page<Ticket> queryAll(TicketQueryParam ticketRequestParam);



    int addByTicketAddParam(TicketAddParam ticketAddParam);

    Ticket queryDetailById(Integer id);

    int cancelByd(int userId, Integer id);

    int releaseById(int userId, Integer id);

    int updateByTicketUpdateParam(TicketUpdateParam ticketUpdateParam);

    Page<Ticket> queryMyAdd(int userId, PageParam pageParam);

    List<String> getAisleByPrice(TicketGetAisleParam ticketGetAsileParam);

    List<String> getRowNumByPriceAndAisle(TicketGetRowNumParam ticketGetRowNumParam);
}
