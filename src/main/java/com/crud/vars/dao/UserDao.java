package com.crud.vars.dao;

import com.crud.vars.model.User;
import com.crud.vars.model.UserDTO;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    User show(int id);

    User findUserByName (String s);

    void save(User user);

    void update(int id, User user);

    void delete(int id);

    void setAdminRole(UserDTO user);

    void setUserRole(UserDTO user);
}
