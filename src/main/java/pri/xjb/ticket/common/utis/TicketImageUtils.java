package pri.xjb.ticket.common.utis;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.lang3.StringUtils;
import pri.xjb.ticket.common.api.BaiduApi;

import pri.xjb.ticket.model.user.response.TicketUserPart;
import pri.xjb.ticket.model.ticketCategory.response.TicketCategory;

import org.springframework.stereotype.Component;
import pri.xjb.ticket.model.ticket.response.Ticket;

/**
 * @author: xjb
 * @date: 2019/8/12
 * @description:
 **/

public class TicketImageUtils {

    //演出日期
    private static final String dayRex = "[0-9]{1,2}[日]";
    //门票价格
    private static final String priceRex = "(255|355|555|755|1255|1655|1855)";
    //内场-所在区域
    private static final String areaRex = "[A-Z]{1}[0-9]{1,2}[区]";

    //看台-所在通道
    private static final String aisleRex = "[0-9]{3}[通道]";

    //所在排数
    private static final String rowNumRex = "[0-9]{1,2}[排]";
    //所在列数
    private static final String columnNumRex = "[0-9]{1,2}[号]";

    public static Ticket recognizeTicket(String filePath) {
        String words = null;
        //识别文字
        try {
//            words = Tess4jUtils.identifyWords(filePath);
            words = BaiduApi.recognizeImage(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(words))
            return null;
        Matcher matcher = null;
        Pattern pattern = null;
        //演出日期
        pattern = Pattern.compile(dayRex);
        matcher = pattern.matcher(words);
        int day = 0;
        while (matcher.find()) {
            String dayString = matcher.group();
            day = Integer.parseInt(dayString.substring(0, dayString.length() - 1));
        }
        int categoryId = 1;
        switch (day) {
            case 23:
                categoryId = 1;
                break;
            case 24:
                categoryId = 2;
                break;
            case 25:
                categoryId = 3;
                break;

        }

        double price = 0;
        pattern = Pattern.compile(priceRex);
        matcher = pattern.matcher(words);
        while (matcher.find()) {
            try {
                price = Double.parseDouble(matcher.group());
            } catch (Exception e) {
                price = 0;
            }


        }
        //区域
        pattern = Pattern.compile(areaRex);
        matcher = pattern.matcher(words);
        String area = null;
        while (matcher.find()) {
            area = matcher.group();
            area = area.substring(0, area.indexOf("区"));
        }

        pattern = Pattern.compile(aisleRex);
        matcher = pattern.matcher(words);
        while (matcher.find()) {
            area = matcher.group();
            area = area.substring(0, area.indexOf("通"));
        }


        //排数
        pattern = Pattern.compile(rowNumRex);
        matcher = pattern.matcher(words);
        int rowNum = 0;
        while (matcher.find()) {
            try {
                String rowString = matcher.group();
                rowNum = Integer.parseInt(rowString.substring(0, rowString.indexOf("排")));
            } catch (Exception e) {
                rowNum = 0;
            }

        }

        //列数
        pattern = Pattern.compile(columnNumRex);
        matcher = pattern.matcher(words);
        int columnNum = 0;
        while (matcher.find()) {
            try {
                String columnString = matcher.group();
                columnNum = Integer.parseInt(columnString.substring(0, columnString.indexOf("号")));
            } catch (Exception e) {
                columnNum = 0;
            }

        }


        Ticket ticket = new Ticket();

        ticket.setTicketCategory(new TicketCategory(categoryId));

        ticket.setPrice(BigDecimal.valueOf(price));
        ticket.setAisle(area);
        ticket.setRow(rowNum);
        ticket.setColumn(columnNum);
        System.out.println(words);
        return ticket;

    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        String filePath = "H:\\xjb\\java\\ticket\\src\\main\\resources\\testImage\\t6.jpg";
        System.out.println(recognizeTicket(filePath));
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1) / 1000);
    }
}
