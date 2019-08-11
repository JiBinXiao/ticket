package pri.xjb.ticket.config.shiro.shiro.bean;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pri.xjb.ticket.common.utis.DESHelper;
import pri.xjb.ticket.common.utis.UserUtils;
import pri.xjb.ticket.model.user.response.TicketUser;
import pri.xjb.ticket.model.user.response.TicketUserPart;
import pri.xjb.ticket.service.TicketUserService;

import java.util.Date;


/**
 * Created by xjb on 2018/11/7
 * 在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。可以说，Realm是专用于安全框架的DAO.
 **/

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserUtils userUtils;

    @Autowired
    TicketUserService userService;


    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();


        TicketUser user = userUtils.getByPhone(username);

        if (user == null) {
            throw new UnknownAccountException("手机号码不存在！");
        }

//        若要限制用户登录失败次数，需要将密码比对 交给密码比较器 RetryLimitHashedCredentialsMatcher
        String password = new String(token.getPassword());
        try {
            password = DESHelper.encrypt(password.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!password.equals(user.getPassword()))
            throw new IncorrectCredentialsException("密码错误！");


        if (user.getSt() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }

        //修改登录时间
        userService.updateltime(user.getId(), new Date());

        return new SimpleAuthenticationInfo(new Principal(user), token.getPassword(), user.getPhone());
    }

    /**
     * 重写方法,清除当前用户的的 授权缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
