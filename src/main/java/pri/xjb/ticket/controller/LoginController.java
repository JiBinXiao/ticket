package pri.xjb.ticket.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pri.xjb.ticket.common.model.RtnResult;
import pri.xjb.ticket.common.utis.UserUtils;
import pri.xjb.ticket.config.shiro.shiro.bean.Principal;


/**
 * @author: xjb
 * @date: 2019/8/9
 * @description:
 **/
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserUtils userUtils;

    /**
     * 没有权限
     *
     * @return
     */
    @RequestMapping(value = "/noAuth")
    public RtnResult noAuth() {
        return RtnResult.noAuthInfo("请先登录");
    }



    /**
     * 判断是否登录
     *
     * @return
     */
    @RequestMapping(value = "/checkLogin")
    public RtnResult checkLogin() {

        //1.获取subject
        Principal principal = userUtils.getPrincipal();
        if (principal == null || principal.getUsername() == null)
            return RtnResult.noAuthInfo("暂未登录");
        else {
            return RtnResult.successInfo("已经登录", principal.getUsername());
        }
    }

}
