package com.vars.spring_boot.dao;

import com.vars.spring_boot.model.User;
import com.vars.spring_boot.model.UserDTO;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    User show(int id);

    User findUserByMail (String s);

    void save(User user);

    void update(int id, User user);

    void delete(int id);

    void setAdminRole(UserDTO user);

    void setUserRole(UserDTO user);
}
