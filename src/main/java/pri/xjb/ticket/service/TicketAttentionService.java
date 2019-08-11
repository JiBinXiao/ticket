package pri.xjb.ticket.service;

import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.common.request.PageParam;
import pri.xjb.ticket.model.attention.response.TicketAttentionResp;

/**
 * @author: xjb
 * @date: 2019/8/11
 * @description:
 **/
public interface TicketAttentionService {
    Page<TicketAttentionResp> queryAll(int userId,PageParam pageParam);

    int add(int userId, Integer tid);

    int deleteByTid(int userId, Integer tid);

    boolean checkIsAttnetion(int userId, Integer tid);
}
