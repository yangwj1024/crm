package com.ywj.crm.web.controller;

import com.ywj.crm.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author ywj
 * @Date 2020/3/26 15:26
 * @Version 1.0
 * 用来映射thymeleaf的controller
 */
@Controller
public class ViewController {

    @RequestMapping("login")
    public String toLoginPage(){
        return "login";
    }

    @RequestMapping("index")
    public String toIndexPage(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user==null){
            return "login";
        }
        return "index";
    }
}
