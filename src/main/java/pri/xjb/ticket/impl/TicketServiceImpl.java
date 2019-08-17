package pri.xjb.ticket.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.common.request.PageParam;
import pri.xjb.ticket.mapper.TicketMapper;
import pri.xjb.ticket.model.ticket.request.*;
import pri.xjb.ticket.model.ticket.response.Ticket;
import pri.xjb.ticket.service.TicketService;

import java.util.List;

/**
 * @author: xjb
 * @date: 2019/8/8
 * @description:
 **/
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;

    @Override
    public Page<Ticket> queryAll(TicketQueryParam ticketRequestParam) {


        Integer totalCount = ticketMapper.queryAllCount(ticketRequestParam);
        Page<Ticket> page = new Page<>(ticketRequestParam.getPageNow(), ticketRequestParam.getPageSize(), totalCount);
        ticketRequestParam.setStartIndex(page.getStartIndex());
        page.setTotalCount(totalCount);
        if (totalCount != 0) {
            List<Ticket> list = ticketMapper.queryAll(ticketRequestParam);
            page.setList(list);
        }

        return page;
    }


    @Override
    public int addByTicketAddParam(TicketAddParam ticketAddParam) {
        return ticketMapper.addByTicketAddParam(ticketAddParam);
    }

    @Override
    public Ticket queryDetailById(Integer id) {
        Ticket ticket = ticketMapper.selectByPrimaryKey(id);
        if (ticket != null)

            if (ticket.getSecrecy() == 1) {
                ticket.setTicketUser(null);
            }

        return ticket;

    }

    @Override
    public int cancelByd(int userId, Integer id) {
        return ticketMapper.cancelByd(userId, id);
    }

    @Override
    public int releaseById(int userId, Integer id) {
        return ticketMapper.releaseById(userId, id);
    }

    @Override
    public int updateByTicketUpdateParam(TicketUpdateParam ticketUpdateParam) {
        return ticketMapper.updateByTicketUpdateParam(ticketUpdateParam);
    }

    @Override
    public Page<Ticket> queryMyAdd(int userId, PageParam pageParam) {
        int totalCount = ticketMapper.queryMyAddCount(userId);
        Page<Ticket> page = new Page<>(pageParam.getPageNow(), pageParam.getPageSize(), totalCount);
        List<Ticket> list = ticketMapper.queryMyAdd(userId, page.getStartIndex(), page.getPageSize());
        page.setList(list);

        return page;
    }

    @Override
    public List<String> getAisleByPrice(TicketGetAisleParam ticketGetAsileParam) {
        return ticketMapper.getAisleByPrice(ticketGetAsileParam);
    }

    @Override
    public List<String> getRowNumByPriceAndAisle(TicketGetRowNumParam ticketGetRowNumParam) {
        return ticketMapper.getRowNumByPriceAndAisle(ticketGetRowNumParam);
    }
}
