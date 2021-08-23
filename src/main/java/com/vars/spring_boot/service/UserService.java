package com.vars.spring_boot.service;

import com.vars.spring_boot.model.User;
import com.vars.spring_boot.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();

    User show(int id);

    void save(UserDTO user);

    void update(int id, UserDTO user);

    void delete(int id);

    void setAdminRole(UserDTO user);

    void setUserRole(UserDTO user);
}
