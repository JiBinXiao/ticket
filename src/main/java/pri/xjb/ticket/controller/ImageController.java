package pri.xjb.ticket.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pri.xjb.ticket.common.model.RtnResult;
import pri.xjb.ticket.common.utis.TicketImageUtils;
import pri.xjb.ticket.common.utis.UserUtils;
import pri.xjb.ticket.model.ticket.response.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: xjb
 * @date: 2019/8/15
 * @description:
 **/
@Api("门票图像识别")
@RestController
@RequestMapping("/image")
public class ImageController {

    @Value("${image.uploadPath}")
    private String imagePath;

    @Autowired
    UserUtils userUtils;


    @ApiOperation(value = "门票图片识别-bash64编码图片")
    @PostMapping("/recognizeTicketByBash64")
    public RtnResult<Ticket> recognizeTicketByBash64(@ApiParam(required = true) @RequestBody String base64Data) {
        Ticket ticket;
        int id = userUtils.getPrincipal().getId();
        if (base64Data.startsWith("\"") && base64Data.endsWith("\"")) {
            base64Data = base64Data.substring(1, base64Data.length() - 1);
        }
        try {

            //文件后缀名
            String dataPrix = "";
            String data = "";
            if (base64Data == null || "".equals(base64Data)) {
                throw new Exception("上传失败，上传图片数据为空");
            } else {
                String[] d = base64Data.split("base64,");
                if (d.length == 2) {
                    dataPrix = d[0];
                    data = d[1];
                } else {
                    throw new Exception("上传失败，数据不合法");
                }
            }
            String suffix = "";
            //判断文件类型
            if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
                //data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
                //data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
                //data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
                //data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            } else {
                throw new Exception("上传图片格式不合法");
            }

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            //封装文件名
            StringBuilder fileName = new StringBuilder();
            fileName.append(id).append("-").
                    append(UUID.randomUUID().toString().replaceAll("-", ""));
            fileName.append(suffix);
            try {
                //使用apache提供的工具类操作流
                String filePath = ImageController.class.getResource("/").getPath() + imagePath;
                //创建文件夹
                File fileFolder = new File(filePath);
                if (!fileFolder.exists()) {
                    fileFolder.mkdir();
                }
                //创建文件
                String savePath = filePath + File.separator + fileName;
                File imageFile = new File(savePath);
                if (!imageFile.exists()) {
                    imageFile.createNewFile();
                }
                //将文件写入服务器
                FileUtils.writeByteArrayToFile(new File(filePath, fileName.toString()), bs);
                //识别图片封装为ticket对象
                ticket = TicketImageUtils.recognizeTicket(savePath);
            } catch (Exception ee) {
                throw new Exception("上传失败，写入文件失败，" + ee.getMessage());
            }


        } catch (Exception e) {

            return RtnResult.errorInfo("文件上传有误", null);
        }


        return RtnResult.successInfo(ticket);
    }


    @ApiOperation(value = "门票图片识别")
    @PostMapping("/recognizeTicket")
    public RtnResult<Ticket> recognizeTicket(@ApiParam(required = true) MultipartFile file) {
        int id = userUtils.getPrincipal().getId();
        if (file == null || file.isEmpty()) {
            return RtnResult.errorInfo("文件不能为空", null);
        }
        StringBuilder fileName = new StringBuilder();
//        int id = 0;
        fileName.append(id).append("-").append(UUID.randomUUID().toString().replaceAll("-", ""));
        String type = file.getContentType();
        if ("image/png".equals(type)) {
            fileName.append(".png");
        } else if ("image/jpeg".equals(type)) {
            fileName.append(".jpeg");
        } else if ("image/gif".equals(type)) {
            fileName.append(".gif");
        } else {
            return RtnResult.errorInfo("请上传图片格式", null);
        }

        String filePath = ImageController.class.getResource("/").getPath() + imagePath;
        File fileFolder = new File(filePath);
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        String savePath = filePath + File.separator + fileName;
        try {
            File imageFile = new File(savePath);
            if (!imageFile.exists()) {
                imageFile.createNewFile();
            }
            file.transferTo(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return RtnResult.errorInfo("文件上传有误", null);
        }
        Ticket ticket = TicketImageUtils.recognizeTicket(savePath);
        return RtnResult.successInfo(ticket);
    }

}
