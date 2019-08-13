package pri.xjb.ticket.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pri.xjb.ticket.common.model.RtnResult;
import pri.xjb.ticket.common.utis.PhoneUtils;
import pri.xjb.ticket.common.utis.UserUtils;
import pri.xjb.ticket.config.shiro.shiro.bean.Principal;
import pri.xjb.ticket.model.user.request.TicketUserAddParam;
import pri.xjb.ticket.model.user.request.TicketUserLoginParam;
import pri.xjb.ticket.model.user.response.TicketUserPart;
import pri.xjb.ticket.model.user.request.TicketUserModifyPwdParam;
import pri.xjb.ticket.model.user.request.TicketUserUpdateParam;
import pri.xjb.ticket.service.TicketUserService;

/**
 * @author: xjb
 * @date: 2019/8/9
 * @description:
 **/
@Api(value = "用户模块")
@RestController
@RequestMapping("/user")
public class TicketUserController {

    private static final Logger logger = LoggerFactory.getLogger(TicketUserController.class);

    @Autowired
    TicketUserService ticketUserService;

    @Autowired
    UserUtils userUtils;

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public RtnResult register(@RequestBody TicketUserAddParam ticketUserAddParam) {

        if (!PhoneUtils.isMobileNO(ticketUserAddParam.getPhone())) {
            return RtnResult.errorInfo("请输入正确的手机号格式！", null);
        }

        if (ticketUserService.getByPhone(ticketUserAddParam.getPhone()) != null) {
            return RtnResult.errorInfo("手机号已注册过！", null);
        }

        int num = ticketUserService.register(ticketUserAddParam);
        if (num == 1)
            return RtnResult.successInfo("注册成功", null);
        else
            return RtnResult.errorInfo("注册失败", null);
    }


    @ApiOperation(value = "更新用户信息", notes = "根据用户主键id更新用户信息")
    @PostMapping("/update")
    public RtnResult update(@RequestBody TicketUserUpdateParam ticketUserUpdateParam) {

        Principal principal = userUtils.getPrincipal();
        ticketUserUpdateParam.setId(principal.getId());
//        ticketUserUpdateParam.setId(2);
        Boolean st = ticketUserService.updateUser(ticketUserUpdateParam);
        if (st) {
            String username=ticketUserUpdateParam.getUsername();

            return RtnResult.successInfo("修改成功", username);
        } else
            return RtnResult.errorInfo("修改失败", null);
    }

    @ApiOperation(value = "修改密码", notes = "根据用户主键id修改密码")
    @PostMapping("/modifyPWD")
    public RtnResult modifyPWD(@ApiParam(value = "修改用户密码实体类") @RequestBody TicketUserModifyPwdParam ticketUserModifyPwdParam) {

        Principal principal = userUtils.getPrincipal();
        int id = principal.getId();
        ticketUserModifyPwdParam.setId(id);

        String pwd = ticketUserModifyPwdParam.getPwd();
        String newPwd = ticketUserModifyPwdParam.getNewPwd();
        //检查参数

        if (StringUtils.isEmpty(pwd)) {
            return RtnResult.errorInfo("pwd参数不能为空", null);
        }
        if (StringUtils.isEmpty(newPwd)) {
            return RtnResult.errorInfo("newPwd参数不能为空", null);
        }
        //检查原密码
        boolean isRight = ticketUserService.checkPWDById(id, pwd);
        if (!isRight)
            return RtnResult.errorInfo("原密码错误", null);
        //修改密码
        if (ticketUserService.updatePwdById(id, newPwd) == 1)
            return RtnResult.successInfo("修改成功", null);
        else
            return RtnResult.errorInfo("修改失败", null);

    }

    @ApiOperation(value = "查询用户详情", notes = "通过用户id查询用户详情")
    @PostMapping("/queryDetail")
    public RtnResult<TicketUserPart> queryDetail() {

        Principal principal = userUtils.getPrincipal();

//        ticketUserUpdateParam.setId(;
        int userId = principal.getId();
        TicketUserPart ticketUser = ticketUserService.queryDetailById(userId);

        return RtnResult.successInfo(ticketUser);

    }


    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public RtnResult login(@ApiParam(value = "登录实体") @RequestBody TicketUserLoginParam ticketUserLoginParam) {

        /**
         * 使用shiro编写认证操作
         */
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token =
                new UsernamePasswordToken(ticketUserLoginParam.getPhone(), ticketUserLoginParam.getPassword());

        //3.执行登录操作
        try {
            logger.info(token.getUsername() + "  开始执行登录操作");
            subject.login(token);
        } catch (UnknownAccountException e) {
            //登录失败，用户名不存在
            logger.info(token.getUsername() + "  用户名不存在");
            return RtnResult.errorInfo("用户名不存在", null);
        } catch (LockedAccountException e) {
            return RtnResult.errorInfo("登录失败次数超过三次", false);
        } catch (IncorrectCredentialsException e) {
            //登录失败，密码错误
            logger.info(token.getUsername() + "  密码错误");
            return RtnResult.errorInfo("密码错误", null);
        }
        String username=userUtils.getByPhone(ticketUserLoginParam.getPhone()).getUsername();


        //logger.info("登录返回的 sessionId:" + subject.getSession().getId());
        return RtnResult.successInfo("成功", username);

    }

    /**
     * 注销
     *
     * @return
     */
    @ApiOperation(value = "注销登录")
    @PostMapping("/logout")
    public RtnResult logout() {
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return RtnResult.successInfo("退出成功", null);
    }

}
