package cn.authing.oauth.demo.file.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    /**
     * 测试方法 受保护资源
     * @param fileName
     * @return
     */
    @GetMapping("file/get")
    public String getFile(String fileName){
        return "fileServer get file " + fileName;
    }

}
