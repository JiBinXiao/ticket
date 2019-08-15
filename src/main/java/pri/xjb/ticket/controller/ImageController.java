package pri.xjb.ticket.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
