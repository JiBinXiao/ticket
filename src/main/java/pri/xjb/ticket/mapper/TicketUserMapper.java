package pri.xjb.ticket.mapper;

import org.apache.ibatis.annotations.Param;
import pri.xjb.ticket.model.user.response.TicketUser;
import pri.xjb.ticket.model.user.response.TicketUserPart;
import pri.xjb.ticket.model.user.request.TicketUserUpdateParam;

import java.util.Date;

public interface TicketUserMapper {
    int deleteByPrimaryKey(Integer id);


    TicketUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketUserUpdateParam ticketUserUpdateParam);


    TicketUser checkPWDById(@Param("id") Integer id, @Param("pwd") String pwd);

    int updatePwdById(@Param("id") Integer id, @Param("encrptPwd") String encrptPwd);

    void updatelstate(TicketUser user);

    TicketUser getByPhone(String phone);

    TicketUserPart queryDetailById(Integer id);

    int register(@Param("username") String username,@Param("phone") String phone, @Param("password") String encrptPwd, @Param("ctime") Date ctime);

    void updateltime(@Param("id")Integer id,@Param("ltime") Date ltime);
}