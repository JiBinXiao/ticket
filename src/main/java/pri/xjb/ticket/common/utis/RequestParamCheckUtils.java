package pri.xjb.ticket.common.utis;

import org.apache.commons.lang3.StringUtils;

/**
 * request请求参数校验
 *  检查是否含有非法参数和防止sql注入
 * Created by xjb on 2019/2/13
 **/
public class RequestParamCheckUtils {
    // 需要过滤的非法字符
    private static  String[] invalidCharacters = new String[]{
            "script", "select", "insert", "document", "window", "function",
            "delete", "update", "prompt", "alert", "create", "alter",
            "drop", "iframe", "link", "where", "replace", "function", "onabort",
            "onactivate", "onafterprint", "onafterupdate", "onbeforeactivate",
            "onbeforecopy", "onbeforecut", "onbeforedeactivateonfocus",
            "onkeydown", "onkeypress", "onkeyup", "onload",
            "expression", "applet", "layer", "ilayeditfocus", "onbeforepaste",
            "onbeforeprint", "onbeforeunload", "onbeforeupdate",
            "onblur", "onbounce", "oncellchange", "oncontextmenu",
            "oncontrolselect", "oncopy", "oncut", "ondataavailable",
            "ondatasetchanged", "ondatasetcomplete", "ondeactivate",
            "ondrag", "ondrop", "onerror", "onfilterchange", "onfinish", "onhelp",
            "onlayoutcomplete", "onlosecapture", "onmouse", "ote",
            "onpropertychange", "onreadystatechange", "onreset", "onresize",
            "onresizeend", "onresizestart", "onrow", "onscroll",
            "onselect", "onstaronsubmit", "onunload", "IMgsrc", "infarction"
    };


    //防止SQL注入的非法字符串
    private static  String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|chr|mid|master|truncate|" +
            "char|declare|sitename|net user|xp_cmdshell|or|+|like'|and|exec|execute|insert|create|drop|" +
            "table|from|grant|use|group_concat|column_name|" +
            "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
            "chr|mid|master|truncate|char|declare|or|--|+|like|//|/|#";//过滤掉的sql关键字，可以手动添加


    /**
     * 检查参数 是否含有非法参数
     * @param paramStr
     * @return
     */
    public static String paramValidate(String paramStr) {
        for (String invalidCharacter : invalidCharacters) {
            if (StringUtils.containsIgnoreCase(paramStr, invalidCharacter)) {

                return paramStr;
            }

        }
        return null;
    }

    /**
     * 检查参数 防止sql注入
     *
     * @param str
     * @return
     */
    public static String sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return badStrs[i];
            }
        }
        return null;
    }

}

