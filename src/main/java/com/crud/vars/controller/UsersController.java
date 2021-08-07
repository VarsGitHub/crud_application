package com.crud.vars.controller;

import com.crud.vars.model.User;
import com.crud.vars.model.UserDTO;
import com.crud.vars.service.UserService;
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
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/user")
    public String user(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User authenticatedUser = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", authenticatedUser);
        return "user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") UserDTO user) {
        return "new";
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

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") UserDTO user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}