package pri.xjb.ticket.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.xjb.ticket.common.model.RtnResult;
import pri.xjb.ticket.common.utis.UserUtils;
import pri.xjb.ticket.config.shiro.shiro.bean.Principal;
import pri.xjb.ticket.service.ContactService;

/**
 * @author: xjb
 * @date: 2019/8/13
 * @description:
 **/
@Api("留言功能")
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    UserUtils userUtils;

    @ApiOperation(value = "留言并发送邮件")
    @PostMapping("/leaveMessage")
    public RtnResult leaveMessage(String message) {
        Principal principal = userUtils.getPrincipal();
        boolean st = contactService.sendMsg(principal, message);
        if (st)
            return RtnResult.successInfo("感谢您的留言~", null);
        else
            return RtnResult.errorInfo("留言失败，请稍后重试", null);

    }
}
