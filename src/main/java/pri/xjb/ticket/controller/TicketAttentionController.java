package pri.xjb.ticket.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.common.model.RtnResult;
import pri.xjb.ticket.common.request.PageParam;
import pri.xjb.ticket.common.utis.UserUtils;
import pri.xjb.ticket.model.attention.response.TicketAttentionResp;
import pri.xjb.ticket.model.ticketCategory.response.TicketCategory;
import pri.xjb.ticket.service.TicketAttentionService;

/**
 * @author: xjb
 * @date: 2019/8/11
 * @description:
 **/
@Api(value = "我关注的门票模块")
@RestController
@RequestMapping("/ticketAttention")
public class TicketAttentionController {

    @Autowired
    private TicketAttentionService ticketAttentionService;

    @Autowired
    UserUtils userUtils;

    @ApiOperation(value = "查询所有我关注的门票")
    @PostMapping("/queryAll")
    public RtnResult<TicketAttentionResp> queryAll(@RequestBody PageParam pageParam) {
        int userId = userUtils.getPrincipal().getId();
//        int userId = 2;
        Page<TicketAttentionResp> page = ticketAttentionService.queryAll(userId, pageParam);
        return RtnResult.successInfo(null, page);
    }

    @ApiOperation(value = "关注门票")
    @PostMapping("/add")
    public RtnResult add(@ApiParam(value = "门票id，不是关注id") @RequestBody Integer tid) {
        int userId = userUtils.getPrincipal().getId();
//        int userId = 2;
        //判断是否已经关注过
        boolean checkIsAttention = ticketAttentionService.checkIsAttnetion(userId, tid);
        if (checkIsAttention)
            return RtnResult.errorInfo("你已关注过，请不要重复关注", null);

        //插入数据库
        int num = ticketAttentionService.add(userId, tid);
        if (num == 1)
            return RtnResult.successInfo("关注成功", null);
        else
            return RtnResult.errorInfo("关注失败", null);
    }

    @ApiOperation(value = "取消关注门票")
    @PostMapping("/cancel")
    public RtnResult cancel(@ApiParam(value = "门票id，不是关注id") @RequestBody Integer tid) {
        int userId = userUtils.getPrincipal().getId();
        //判断是否已经关注过
        boolean checkIsAttention = ticketAttentionService.checkIsAttnetion(userId, tid);
        if (!checkIsAttention)
            return RtnResult.errorInfo("你已取消关注了，请不要重复操作！", null);

        //真实删除
        int num = ticketAttentionService.deleteByTid(userId, tid);
        if (num == 1)
            return RtnResult.successInfo("取消关注成功", null);
        else
            return RtnResult.errorInfo("取消关注失败", null);
    }


}
