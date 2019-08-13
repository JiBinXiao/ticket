package pri.xjb.ticket.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.xjb.ticket.common.model.Page;
import pri.xjb.ticket.common.utis.DESHelper;
import pri.xjb.ticket.common.utis.SongNameUtils;
import pri.xjb.ticket.mapper.TicketUserMapper;
import pri.xjb.ticket.model.user.request.TicketUserAddParam;
import pri.xjb.ticket.model.user.response.TicketUser;
import pri.xjb.ticket.model.user.response.TicketUserPart;
import pri.xjb.ticket.model.user.request.TicketUserUpdateParam;
import pri.xjb.ticket.service.TicketUserService;

import java.util.Date;

/**
 * @author: xjb
 * @date: 2019/8/8
 * @description:
 **/
@Service
public class TickUserServiceImpl implements TicketUserService {

    @Autowired
    TicketUserMapper ticketUserMapper;


    @Override
    public TicketUser getByUserName(String username) {
        return null;
    }

    @Override
    public void updateltime(Integer id, Date date) {

        ticketUserMapper.updateltime(id, date);
    }

    @Override
    public TicketUser getById(Integer id) {
        return ticketUserMapper.selectByPrimaryKey(id);
    }


    @Override
    public Boolean updateUser(TicketUserUpdateParam ticketUserUpdateParam) {
        return ticketUserMapper.updateByPrimaryKeySelective(ticketUserUpdateParam) == 1;
    }


    @Override
    public boolean checkPWDById(Integer id, String pwd) {
        String encrptPwd = null;
        try {
            encrptPwd = DESHelper.encrypt(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketUserMapper.checkPWDById(id, encrptPwd) != null;
    }

    @Override
    public int updatePwdById(Integer id, String newPwd) {
        String encrptPwd = null;
        try {
            encrptPwd = DESHelper.encrypt(newPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketUserMapper.updatePwdById(id, encrptPwd);
    }

    @Override
    public TicketUserPart queryDetailById(Integer id) {
        return ticketUserMapper.queryDetailById(id);
    }

    @Override
    public void updatelstate(TicketUser user) {
        ticketUserMapper.updatelstate(user);
    }

    @Override
    public TicketUser getByPhone(String phone) {
        return ticketUserMapper.getByPhone(phone);
    }

    @Override
    public int register(TicketUserAddParam ticketUserAddParam) {
        String encrptPwd = null;
        try {
            encrptPwd = DESHelper.encrypt(ticketUserAddParam.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticketUserMapper.register(SongNameUtils.getRandomSongName(), ticketUserAddParam.getPhone(), encrptPwd, new Date());
    }
}
