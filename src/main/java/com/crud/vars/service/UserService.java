package com.crud.vars.service;

import com.crud.vars.model.User;
import com.crud.vars.model.UserDTO;
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
