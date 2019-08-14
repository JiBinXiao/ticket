package pri.xjb.ticket.common.utis;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.lang3.StringUtils;
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
    //所在区域
    private static final String areaRex = "[A-Z]{1}[0-9]{1,2}[区]";
    //所在排数
    private static final String rowNumRex = "[0-9]{1,2}[排]";
    //所在列数
    private static final String columnNumRex = "[0-9]{1,2}[号]";

    public static Ticket recognition(String filePath) {
        String words = null;
        try {
            words = Tess4jUtils.identifyWords(filePath);
        } catch (TesseractException | IOException e) {
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

        //区域
        pattern = Pattern.compile(areaRex);
        matcher = pattern.matcher(words);
        String area = null;
        while (matcher.find()) {
            area = matcher.group();
            area = area.substring(0, area.length() - 1);
        }

        //排数
        pattern = Pattern.compile(rowNumRex);
        matcher = pattern.matcher(words);
        String rowNum = null;
        while (matcher.find()) {
            rowNum = matcher.group();
        }

        //列数
        pattern = Pattern.compile(columnNumRex);
        matcher = pattern.matcher(words);
        String columnNum = null;
        while (matcher.find()) {
            columnNum = matcher.group();
        }


        Ticket ticket = new Ticket();

        ticket.setTicketCategory(new TicketCategory(categoryId));

        ticket.setPrice(new BigDecimal("0"));
        ticket.setAisle(area);

//        ticket.setRow(rowNum);
//        ticket.setColumn(columnNum);
        System.out.println(ticket);


        return ticket;

    }

    public static void main(String[] args) {
        String filePath = "H:\\xjb\\java\\ticket\\src\\main\\resources\\testImage\\a1.jpg";
        recognition(filePath);
    }
}
