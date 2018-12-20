package cn.edu.ncu.bookstore.controller;

import cn.edu.ncu.bookstore.entity.Comment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

//@RestController 返回原有数据而非解析html
@RestController
public class FileUploadController {

    //上传文件
    @RequestMapping("/postCommentImage")
    public String postCommentImage(@RequestParam(value = "image", required = false)MultipartFile image) {
        System.out.println("upload image");
        if(image.isEmpty()) {
            return "redirect: json/false.json";
        }
        Comment comment = new Comment();
        String imageName = image.getOriginalFilename();
        imageName = UUID.randomUUID().toString().replace("-", "")+imageName.substring(imageName.lastIndexOf('.'));
        Path savePath = Paths.get("src/main/resources/static/img/comment/"+imageName);
        System.out.println(savePath);
        //要上传的目标文件存放路径
       // String localPath = "E:/mydata/"+imageName;
        try {
            byte[] bytes = image.getBytes();
            Files.write(savePath, bytes);
            //File file = new File(localPath);
          //  image.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "img/comment/"+imageName;
    }
}
