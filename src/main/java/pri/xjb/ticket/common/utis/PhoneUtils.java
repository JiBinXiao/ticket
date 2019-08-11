package pri.xjb.ticket.common.utis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: xjb
 * @date: 2019/8/11
 * @description:
 **/
public class PhoneUtils {
    public static boolean isMobileNO(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {

            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
           
            return isMatch;
        }


    }
}
