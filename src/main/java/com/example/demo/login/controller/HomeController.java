package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    private Map<String, String> radioMarriage;

    private Map<String, String> initRadioMarriage(){
        Map<String, String> radio = new LinkedHashMap<String, String>();

        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    @GetMapping("/home")
    public String getHome(Model model) {

        model.addAttribute("contents", "login/home :: home_contents");

        return "login/homeLayout";
    }

    @PostMapping("/logout")
    public String postLogout(){

        return "/login/login";
    }

    @GetMapping("/userList")
    public String getUserList(Model model) {
        model.addAttribute("contents", "login/userList :: userList_contents");

        List<User> userList = userService.selectMany();

        model.addAttribute("userList", userList);

        int count = userService.count();
        model.addAttribute("userListCount", count);

        return "login/homeLayout";
    }
}