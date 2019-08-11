package pri.xjb.ticket.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.common.request.PageParam;
import pri.xjb.ticket.mapper.TicketAttentionMapper;
import pri.xjb.ticket.model.attention.TicketAttention;
import pri.xjb.ticket.model.attention.response.TicketAttentionResp;
import pri.xjb.ticket.service.TicketAttentionService;

import java.util.Date;
import java.util.List;

/**
 * @author: xjb
 * @date: 2019/8/11
 * @description:
 **/
@Service
public class TicketAttentionServiceImpl implements TicketAttentionService {

    @Autowired
    TicketAttentionMapper ticketAttentionMapper;

    @Override
    public Page<TicketAttentionResp> queryAll(int userId, PageParam pageParam) {

        int totalCount = ticketAttentionMapper.queryAllCount(userId);
        Page<TicketAttentionResp> page = new Page<>(pageParam.getPageNow(), pageParam.getPageSize(), totalCount);

        if (totalCount != 0) {
            List<TicketAttentionResp> list = ticketAttentionMapper.queryAll(userId, page.getStartIndex(), page.getPageSize());
            page.setList(list);
        }

        return page;
    }

    @Override
    public int add(int userId, Integer tid) {

        return ticketAttentionMapper.add(userId, tid, new Date());
    }

    @Override
    public int deleteByTid(int userId, Integer tid) {
        return ticketAttentionMapper.deleteByTid(userId,tid);
    }

    @Override
    public boolean checkIsAttnetion(int userId, Integer tid) {

        TicketAttention ticketAttention=ticketAttentionMapper.selectByUserIdAndTid(userId,tid);

        return ticketAttention !=null;
    }
}
