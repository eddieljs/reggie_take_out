package com.ljs.reggie.controller;

import com.ljs.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 功能：
 * 作者：ljs
 * 文件上传下载
 * 日期：2024/8/27 10:56
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;//yml中定义的路径
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info("文件上传：{}",file.toString());
        try {
            //临时文件转存到指定位置
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始扩展名后缀xxx.jpg
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String FileName = UUID.randomUUID() + extension;
            file.transferTo(new File(basePath + FileName));
            return R.success(FileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
            return R.success("上传成功");
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            //输入流 通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            //输出流 通过输出流将文件写回浏览器 在浏览器展示文件
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}