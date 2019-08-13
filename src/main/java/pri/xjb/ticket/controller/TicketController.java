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
import pri.xjb.ticket.model.ticket.request.*;
import pri.xjb.ticket.model.ticket.response.Ticket;
import pri.xjb.ticket.service.TicketAttentionService;
import pri.xjb.ticket.service.TicketService;

import java.util.List;

/**
 * @author: xjb
 * @date: 2019/8/8
 * @description:
 **/
@Api(value = "门票模块")
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    UserUtils userUtils;

    @Autowired
    TicketAttentionService ticketAttentionService;


    @ApiOperation(value = "查询我发布的门票", notes = "分页查询我发布的门票")
    @PostMapping("/queryMyAdd")
    public RtnResult<Page<Ticket>> queryMyAdd(@RequestBody PageParam pageParam) {
        int userId = userUtils.getPrincipal().getId();
//        int userId = 2;
        Page<Ticket> page = ticketService.queryMyAdd(userId, pageParam);
        return RtnResult.successInfo(null, page);
    }


    @ApiOperation(value = "查询门票", notes = "根据参数查询门票")
    @PostMapping("/queryAll")
    public RtnResult<Page<Ticket>> queryAll(@RequestBody TicketQueryParam ticketRequestParam) {
        Page<Ticket> page = ticketService.queryAll(ticketRequestParam);
        return RtnResult.successInfo(null, page);
    }

    @ApiOperation(value = "修改门票信息", notes = "修改门票信息")
    @PostMapping("/update")
    public RtnResult update(@RequestBody TicketUpdateParam ticketUpdateParam) {
        int userId = userUtils.getPrincipal().getId();
//        int userId = 1;

        int size = ticketService.updateByTicketUpdateParam(ticketUpdateParam);
        if (size == 1)
            return RtnResult.successInfo("修改成功", null);
        else
            return RtnResult.errorInfo("修改失败", null);
    }


    @ApiOperation(value = "新增门票", notes = "新增门票")
    @PostMapping("/add")
    public RtnResult add(@RequestBody TicketAddParam ticketAddParam) {
        int userId = userUtils.getPrincipal().getId();
//        int userId = 0;
        //检查此门票是否已经被发布过

        //新增门票
        ticketAddParam.setCuser(userId);
        ticketAddParam.setUuser(userId);
        int size = ticketService.addByTicketAddParam(ticketAddParam);
        if (size == 1)
            return RtnResult.successInfo("添加成功", null);
        else
            return RtnResult.errorInfo("添加失败", null);
    }

    @ApiOperation(value = "查询门票详情", notes = "通过门票id查询门票详情")
    @PostMapping("/queryDetailById")
    public RtnResult<Ticket> queryDetailById(@ApiParam(name = "id", value = "门票id") @RequestBody Integer id) {
        int userId = userUtils.getPrincipal().getId();

        Ticket ticket = ticketService.queryDetailById(id);
        if(ticket ==null)
            return RtnResult.errorInfo("暂无数据",null);
        boolean isAttention = ticketAttentionService.checkIsAttnetion(userId,id);
        ticket.setAttention(isAttention);
        return RtnResult.successInfo(ticket);

    }

    @ApiOperation(value = "取消发布门票", notes = "通过门票id取消发布")
    @PostMapping("/cancelById")
    public RtnResult cancelById(@ApiParam(name = "id", value = "门票id") @RequestBody Integer id) {
        int userId = userUtils.getPrincipal().getId();
//        int userId = 1;
        int size = ticketService.cancelByd(userId, id);

        if (size == 1)
            return RtnResult.successInfo("取消发布成功", null);
        else
            return RtnResult.errorInfo("取消发布失败", null);
    }

    @ApiOperation(value = "重新发布门票", notes = "通过门票id发布")
    @PostMapping("/releaseById")
    public RtnResult releaseById(@ApiParam(name = "id", value = "门票id") @RequestBody Integer id) {
        int userId = userUtils.getPrincipal().getId();
        int size = ticketService.releaseById(userId, id);

        if (size == 1)
            return RtnResult.successInfo("发布成功", null);
        else
            return RtnResult.errorInfo("发布失败", null);
    }

    @ApiOperation(value = "根据票价得到通道号", notes = "根据票价得到通道号")
    @PostMapping("/getAisleByPrice")
    public RtnResult getAisleByPrice(@ApiParam(name = "id", value = "门票id") @RequestBody TicketGetAisleParam ticketGetAsileParam) {

        List<String> list = ticketService.getAisleByPrice(ticketGetAsileParam);
        return RtnResult.successInfo("成功", list);

    }

    @ApiOperation(value = "根据票价和通道号得到行号", notes = "根据票价和通道号得到行号")
    @PostMapping("/getRowNumByPriceAndAisle")
    public RtnResult getRowNumByPriceAndAisle(@ApiParam(name = "id", value = "门票id") @RequestBody TicketGetRowNumParam ticketGetRowNumParam) {

        List<String> list = ticketService.getRowNumByPriceAndAisle(ticketGetRowNumParam);

        return RtnResult.successInfo("成功", list);

    }
}
