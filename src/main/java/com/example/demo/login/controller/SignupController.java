package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    private Map<String, String> radioGender;

    private Map<String, String> initRadioGender(){

        Map<String, String> radio = new LinkedHashMap<>();

        radio.put("男性", "true");
        radio.put("女性", "false");

        return radio;
    }

    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {

        radioGender = initRadioGender();

        model.addAttribute("radioGender", radioGender);

        return "login/signup";
    }

    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return getSignUp(form, model);
        }

        System.out.println(form);

        User user = new User();

        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setGender(form.isGender());
        user.setRole("ROLE_GENERAL");//一般用ロール

        boolean result = userService.insert(user);
        int count = userService.count();

        if (result == true) {
            System.out.println("成功");
            System.out.println(count);
        } else {
            System.out.println("失敗");
        }

        return "redirect:/login";
    }
}
