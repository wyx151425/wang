package com.rumofuture.wzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

@Controller
@RequestMapping("/mvc")
public class MainController {

    @GetMapping(value = "/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String logInPage() {
        return "logIn";
    }

    @GetMapping(value = "/signUp")
    public String signUpPage() {
        return "signUp";
    }

    @GetMapping(value = "/userInfo")
    public String userInfoPage() {
        return "userInfo";
    }

    @GetMapping(value = "/passwordUpdate")
    public String userPasswordUpdatePage() {
        return "passwordUpdate";
    }

    @GetMapping(value = "/team")
    public String teamPage() {
        return "team";
    }

    @GetMapping(value = "/teamManage")
    public String teamManagerPage() {
        return "teamManage";
    }

    @GetMapping(value = "/memberAdd")
    public String memberAddPage() {
        return "memberAdd";
    }

    @GetMapping(value = "/memberInfo")
    public String memberInfoPage(@RequestParam("id") Integer id) {
        return "memberInfo";
    }
}
