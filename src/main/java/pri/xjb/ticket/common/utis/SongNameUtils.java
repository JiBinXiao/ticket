package pri.xjb.ticket.common.utis;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: xjb
 * @date: 2019/8/13
 * @description:
 **/

public class SongNameUtils {
    private static final Logger logger = LoggerFactory.getLogger(SongNameUtils.class);


    public static List<String> mayDaySongName = new ArrayList<>();

    public static void initName() {
        String filePath = SongNameUtils.class.getResource("/MAYDAYSongName.txt").getPath();
        List<String> nameList = new ArrayList<>();
        try {
            nameList = FileUtils.readLines(new File(filePath), "utf-8");
        } catch (IOException e) {
            logger.error(filePath + " 的歌名初始化失败！！！");
            e.printStackTrace();
        }
        //排重
        Set<String> nameSet = new HashSet<>(nameList);
        //放入集合
        mayDaySongName = new ArrayList<>(nameSet);
        logger.info(filePath + " 的歌名初始化成功！！！共" + mayDaySongName.size() + "条");
    }

    public static String getRandomSongName() {
        if (mayDaySongName == null || mayDaySongName.isEmpty())
            return null;
        Random random = new Random();
        int index = random.nextInt(mayDaySongName.size());
        return mayDaySongName.get(index);
    }

    public static void main(String[] args) {
        initName();
        System.out.println(getRandomSongName());
    }

}
