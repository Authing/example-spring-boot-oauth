package cn.authing.oauth.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {


    @RequestMapping("/loginByPassword")
    public String loginPage(){
        return "loginByPassword.html";
    }

    /**
     * 测试方法 受保护资源
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("echo")
    public String user(String id){
        return "echo"+id;
    }
}
