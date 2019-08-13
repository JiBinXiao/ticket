package pri.xjb.ticket.service;

import pri.xjb.ticket.config.shiro.shiro.bean.Principal;

/**
 * @author: xjb
 * @date: 2019/8/13
 * @description:
 **/
public interface ContactService {
    boolean sendMsg(Principal principal, String message);
}
