package com.vars.spring_boot.controller;

import com.vars.spring_boot.model.User;
import com.vars.spring_boot.model.dto.UserDTO;
import com.vars.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/table")
    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> userDTOS = userService.getUserDTOS();
        return ResponseEntity.ok(userDTOS);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
        User user1 = userService.saveOrUpdate(user);
        return ResponseEntity.ok(new UserDTO(user1));
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        return ResponseEntity.ok(new UserDTO(userService.show(id)));
    }

    @GetMapping(value = "/getAuth", produces = "application/json")
    public ResponseEntity<UserDTO> getAuth() {
        SecurityContext context = SecurityContextHolder.getContext();
        User tmp = (User) context.getAuthentication().getPrincipal();
        return ResponseEntity.ok(new UserDTO(tmp));
    }

    @DeleteMapping("/del")
    public ResponseEntity<?> delUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.delete(user.getId()));
    }
}
