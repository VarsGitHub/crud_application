package com.crud.vars.dao;

import com.crud.vars.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    User show(int id);

    void save(User user);

    void update(int id, User user);

    void delete(int id);
}
