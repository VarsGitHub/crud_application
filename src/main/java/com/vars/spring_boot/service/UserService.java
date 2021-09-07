package com.vars.spring_boot.service;

import com.vars.spring_boot.model.User;
import com.vars.spring_boot.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Iterable<User> getUsers();

    User show(int id);

    User saveOrUpdate(UserDTO user);

    boolean delete(int id);

    List<UserDTO> getUserDTOS();
}
