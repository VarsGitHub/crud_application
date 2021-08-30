package com.vars.spring_boot.controller;


import com.vars.spring_boot.model.User;
import com.vars.spring_boot.model.UserDTO;
import com.vars.spring_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String index(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User authenticatedUser = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("authInfo", authenticatedUser);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", new UserDTO());
        return "index";
    }

    @GetMapping("/user")
    public String user(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User authenticatedUser = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("authInfo", authenticatedUser);
        return "user";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") UserDTO user) {
        if (user.getAdmin()) {
            userService.setAdminRole(user);
        }
        userService.setUserRole(user);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") UserDTO user) {
        userService.update(user.getId(), user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("user") UserDTO user) {
        userService.delete(user.getId());
        return "redirect:/admin";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}