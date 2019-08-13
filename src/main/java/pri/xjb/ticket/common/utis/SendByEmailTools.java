package pri.xjb.ticket.common.utis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * 邮件发送的工具类
 */
@Component
public class SendByEmailTools {
    private static final Logger logger = LoggerFactory.getLogger(SendByEmailTools.class);
    @Autowired
    JavaMailSender jms;


    /**
     * 发送html模版
     *
     * @param sender       发送人
     * @param receiver     收件人
     * @param copyTo       抄送人多值用 ; 分割
     * @param title        标题
     * @param emailContent html内容
     * @return
     */
    public boolean sendMsg(String sender, String receiver, String copyTo, String title, String emailContent) {
        String[] receiverArray = {};
        if (StringUtils.isNotEmpty(receiver)) {
            receiverArray = receiver.split(";");
        }

        String[] cc = {};
        if (StringUtils.isNotEmpty(copyTo)) {
            cc = copyTo.split(";");
        }
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            try {
                helper = new MimeMessageHelper(message, true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            //发件人
            //此处测试代码注意删除
            sender = "xjbbiubiu@163.com";
            helper.setFrom(sender);
            //收件人

            if (receiverArray.length > 0) {
                helper.setTo(receiverArray);
            }
//            helper.setTo(receiver);
            if (cc.length > 0)
                helper.setCc(cc);
            helper.setSubject(title);
            helper.setText(emailContent, false);
            jms.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }


        return true;

    }
}
