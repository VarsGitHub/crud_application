package com.crud.vars.service;

import com.crud.vars.dao.UserDao;
import com.crud.vars.model.User;
import com.crud.vars.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User show(int id) {
        return userDao.show(id);
    }

    @Override
    public void save(UserDTO user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setMail(user.getMail());
        newUser.setRoles(user.getRoles());
        userDao.save(newUser);
    }

    @Override
    public void update(int id, UserDTO user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setMail(user.getMail());
        newUser.setRoles(user.getRoles());
        userDao.update(id, newUser);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findUserByName(s);
    }

    @Override
    public void setAdminRole(UserDTO user) {
        userDao.setAdminRole(user);
    }

    @Override
    public void setUserRole(UserDTO user) {
        userDao.setUserRole(user);
    }
}
