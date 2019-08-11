package pri.xjb.ticket.service;

import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.common.request.PageParam;
import pri.xjb.ticket.model.ticketCategory.response.TicketCategory;

/**
 * @author: xjb
 * @date: 2019/8/8
 * @description:
 **/
public interface TicketCategoryService {
    Page<TicketCategory> queryAll(PageParam pageParam);
}
