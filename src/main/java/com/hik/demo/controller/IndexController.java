package com.hik.demo.controller;

import com.hik.demo.service.MyCasService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Creat by kevin on 2018/8/9
 */
@Controller
@EnableAutoConfiguration
public class IndexController {
    @Autowired
    private MyCasService myCasService;

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);
    //测试spring mvc
    @RequestMapping("/u")
    public String index() {
        System.out.println("9999999999999999");
        return "index";
    }
    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password){
        logger.info("doLogin() 方法被调用");
        // 创建Subject实例
        Subject currentUser = SecurityUtils.getSubject();
        // 将用户名及密码封装到UsernamePasswordToken
//        String newPwd =
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try{
            currentUser.login(token);
            //// 判断当前用户是否登录
            if(currentUser.isAuthenticated() == true){
                return "deviceManager";
            }
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("登录失败");
        }

        return "login";

    }
    @RequestMapping("/doRegister")
    public String doRegister(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        logger.info("login() 方法被调用");
        boolean result = myCasService.registerData(username,password);
        if (result){
            return "login";
        }
        return "register";
    }

    @RequestMapping("/login")
    public String login(){
        logger.info("login() 方法被调用");

        return "login";
    }
    @RequestMapping("/register")
    public String register(){
        logger.info("register() 方法被调用");
        return "register";
    }


}
