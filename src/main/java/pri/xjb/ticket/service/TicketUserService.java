package pri.xjb.ticket.service;

import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.model.user.request.TicketUserAddParam;
import pri.xjb.ticket.model.user.response.TicketUser;
import pri.xjb.ticket.model.user.response.TicketUserPart;
import pri.xjb.ticket.model.user.request.TicketUserUpdateParam;

import java.util.Date;

/**
 * @author: xjb
 * @date: 2019/8/8
 * @description:
 **/
public interface TicketUserService {


    public TicketUser getByUserName(String username);

    void updateltime(Integer id, Date date);

    TicketUser getById(Integer id);



    Boolean updateUser(TicketUserUpdateParam ticketUserUpdateParam);


    boolean checkPWDById(Integer id, String pwd);

    int updatePwdById(Integer id, String newPwd);

    TicketUserPart queryDetailById(Integer id);

    void updatelstate(TicketUser user);

    TicketUser getByPhone(String phone);

    int register(TicketUserAddParam ticketUserAddParam);
}
