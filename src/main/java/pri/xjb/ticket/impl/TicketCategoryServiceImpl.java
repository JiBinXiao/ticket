package pri.xjb.ticket.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.common.request.PageParam;
import pri.xjb.ticket.common.utis.DateUtils;
import pri.xjb.ticket.mapper.TicketCategoryMapper;
import pri.xjb.ticket.model.ticketCategory.response.TicketCategory;
import pri.xjb.ticket.service.TicketCategoryService;

import java.util.Date;
import java.util.List;

/**
 * @author: xjb
 * @date: 2019/8/8
 * @description:
 **/
@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

    @Autowired
    TicketCategoryMapper ticketCategoryMapper;


    @Override
    public Page<TicketCategory> queryAll(PageParam pageParam) {
        int totalCount = ticketCategoryMapper.queryAllCount();
        Page<TicketCategory> page = new Page<>(pageParam.getPageNow(), pageParam.getPageSize(), totalCount);
        List<TicketCategory> list = ticketCategoryMapper.queryAll(page.getPageSize(), page.getStartIndex());
        if (!list.isEmpty()) {
            list.forEach(item -> {
                Date sdate = item.getSdate();
                Date edate = item.getEdate();
                String date = DateUtils.getMonth(sdate) + "月" + DateUtils.getDay(sdate) + "日";

                String time = DateUtils.getHours(sdate) + ":" + DateUtils.getMinutes(sdate)
                        + "-" + DateUtils.getHours(edate) + ":" + DateUtils.getMinutes(edate);
                item.setDate(date);
                item.setTime(time);
            });
        }
        page.setList(list);
        return page;
    }
}
