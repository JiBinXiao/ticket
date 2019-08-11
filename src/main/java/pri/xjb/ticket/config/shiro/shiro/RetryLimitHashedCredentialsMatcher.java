package pri.xjb.ticket.config.shiro.shiro;


import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pri.xjb.ticket.common.utis.DESHelper;
import pri.xjb.ticket.common.utis.UserUtils;
import pri.xjb.ticket.model.user.response.TicketUser;
import pri.xjb.ticket.model.user.response.TicketUserPart;
import pri.xjb.ticket.service.TicketUserService;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: WangSaiChao
 * @date: 2018/5/25
 * @description: 登陆次数限制
 * Created by xjb on 2018/11/29
 **/
public class RetryLimitHashedCredentialsMatcher extends SimpleCredentialsMatcher {
    private static final Logger logger = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

    @Autowired
    private TicketUserService userService;
    public static final String DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX = "shiro:cache:retrylimit:";
    private String keyPrefix = DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX;

    @Autowired
    UserUtils userUtils;

    private RedisManager redisManager;

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    private String getRedisKickoutKey(String username) {
        return this.keyPrefix + username;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        //获取用户名
        String username = (String) token.getPrincipal();
        //获取用户登录次数
        AtomicInteger retryCount = (AtomicInteger) redisManager.get(getRedisKickoutKey(username));
        if (retryCount == null) {
            //如果用户没有登陆过,登陆次数加1 并放入缓存
            retryCount = new AtomicInteger(0);
        }
        //判断用户账号和密码是否正确
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken)token ;
        String password=new String(usernamePasswordToken.getPassword());
//        boolean matches = super.doCredentialsMatch(token, info);
        boolean matches=checkPWD(username,password);
        //锁定账户
        if (retryCount.incrementAndGet() > 3 && !matches) {

            //如果用户登陆失败次数大于3次 抛出锁定用户异常  并修改数据库字段
            TicketUser user = userService.getByUserName(username);
//            if (user != null && user.getState() == 0) {
//                //数据库字段 默认为 0  就是正常状态 所以 要改为1
//                //修改数据库的状态字段为锁定
//                user.setState(1);
//                userService.updatelstate(user);
//            }
//            logger.info("锁定用户" + user.getUsername());
            logger.info("登录次数超过三次！！！用户" + user.getUsername());
            //抛出用户锁定异常
            throw new LockedAccountException();
        }


        if (matches) {
            //如果正确,从缓存中将用户登录计数 清除
            redisManager.del(getRedisKickoutKey(username));
        }else
        {

            redisManager.set(getRedisKickoutKey(username), retryCount);
            throw new IncorrectCredentialsException("密码错误！");
        }
        return matches;
    }

    /**
     * 判断用户名密码是否匹配
     * @param username
     * @param password
     * @return
     */
    private boolean checkPWD(String phone,String password){
        //获取密码

        TicketUser user= userUtils.getByPhone(phone);
        try {
            password= DESHelper.encrypt(password.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!password.equals(user.getPassword())){
            return false;
        }


        return true;

    }

    /**
     * 根据用户名 解锁用户
     *
     * @param username
     * @return
     */
    public void unlockAccount(String username) {
        TicketUser user = userService.getByUserName(username);
        if (user != null) {
            //修改数据库的状态字段为未锁定
            user.setSt(0);
            userService.updatelstate(user);
            redisManager.del(getRedisKickoutKey(username));
        }
    }
}
