package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/logins")
public class LoginController {


    @GetMapping({"/login"})
    @ResponseBody
    public String login() {
        return "login";
    }


}
