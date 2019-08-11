package pri.xjb.ticket.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.common.model.RtnResult;
import pri.xjb.ticket.common.request.PageParam;
import pri.xjb.ticket.model.ticketCategory.response.TicketCategory;
import pri.xjb.ticket.service.TicketCategoryService;

/**
 * @author: xjb
 * @date: 2019/8/8
 * @description:
 **/
@Api(value = "门票分类模块")
@RestController
@RequestMapping("/ticketCategory")
public class TicketCategoryController {

    @Autowired
    TicketCategoryService ticketCategoryService;

    @ApiOperation(value = "查询所有门票分类", notes = "无条件查询所有门票分类")
    @PostMapping("/queryAll")
    public RtnResult<TicketCategory> queryAll(@RequestBody PageParam pageParam) {
        Page<TicketCategory> page = ticketCategoryService.queryAll(pageParam);
        return RtnResult.successInfo(null, page);
    }
}
