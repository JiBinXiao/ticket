package pri.xjb.ticket.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.xjb.ticket.common.utis.SendByEmailTools;
import pri.xjb.ticket.config.shiro.shiro.bean.Principal;
import pri.xjb.ticket.service.ContactService;

/**
 * @author: xjb
 * @date: 2019/8/13
 * @description:
 **/
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    SendByEmailTools sendByEmailTools;

    private final String sender = "xjbbiubiu@163.com";

    private final String receiver = "941623975@qq.com";

    @Override
    public boolean sendMsg(Principal principal, String message) {

        int id = principal.getId();
        String username = principal.getUsername();
        String title = "【五月天门票统计平台】收到【" + username + "】的留言";
        String content = "id:" + id + "\n用户名：" + username + "\n给您留言：" + message;

        return sendByEmailTools.sendMsg(sender, receiver, null, title, content);
    }
}
