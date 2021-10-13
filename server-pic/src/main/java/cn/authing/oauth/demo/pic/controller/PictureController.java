package cn.authing.oauth.demo.pic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PictureController {
    /**
     * 测试方法 受保护资源
     * @param name
     * @return
     */
    @GetMapping("pic/show")
    public String show(String name){
        return "pictureServer show picture" + name;
    }

}
