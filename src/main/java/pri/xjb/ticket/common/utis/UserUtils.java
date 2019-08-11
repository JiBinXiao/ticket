package pri.xjb.ticket.common.utis;


import pri.xjb.ticket.config.shiro.shiro.bean.Principal;
import pri.xjb.ticket.model.user.response.TicketUser;
import pri.xjb.ticket.model.user.response.TicketUserPart;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pri.xjb.ticket.service.TicketUserService;


/**
 * Created by xjb on 2018/11/29
 * 考虑将用户信息放入缓存
 **/
@Component
public class UserUtils {

    private static final Logger logger = LoggerFactory.getLogger(UserUtils.class);



    @Autowired
    private  TicketUserService userService;


    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    public  TicketUser get(Integer id){
        if(id == null){
            return null;
        }
        TicketUser user = userService.getById(id);
        if(user!=null)
            return user;
        else
            return null;
    }

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    public  TicketUser getByLoginName(String username){
        TicketUser TicketUser=userService.getByUserName(username);
        return TicketUser;
    }

    /**
     * 获得当前用户详细信息
     * @return  取不到则返回 new User()
     */
    public  TicketUser getUser(){
        Principal principal=getPrincipal();
        if(principal!=null){
            TicketUser user = get(principal.getId());
            if(user!=null)
                return user;

            return new TicketUser();
        }

        // 如果没有登录，则返回实例化空的User对象。
        return new TicketUser();


    }

    /**
     * 获取当前登录者授权对象
     */
    public  Principal getPrincipal(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Principal principal= (Principal) subject.getPrincipal();
            if (principal != null){
                return principal;
            }
        }catch (UnavailableSecurityManagerException e) {

        }catch (InvalidSessionException e){

        }
        return null;
    }

    /**
     * 得到当前session
     * @return
     */
    public  Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){

        }
        return null;
    }


    public  TicketUser getByPhone(String username) {
        return userService.getByPhone(username);
    }
}
