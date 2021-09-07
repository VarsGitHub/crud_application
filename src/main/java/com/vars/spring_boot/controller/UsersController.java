package com.vars.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

    @GetMapping("/admin")
    public String userForm() {
        return "index";
    }

    @GetMapping("/user")
    public String showUser() {
        return "user";
    }

    @GetMapping("/")
    public String showLogin() {
        return "redirect:/login";
    }
}