package com.vars.spring_boot.dao;

import com.vars.spring_boot.model.Role;
import com.vars.spring_boot.model.User;
import com.vars.spring_boot.model.UserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByMail(String s) {
        Query query = entityManager.createQuery("SELECT a FROM User a WHERE a.mail = :mail", User.class);
        query.setParameter("mail", s);
        User result = (User) query.getSingleResult();
        return result;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        User userToUpdate = entityManager.find(User.class, id);
        userToUpdate.setName(user.getName());
        userToUpdate.setMail(user.getMail());
        userToUpdate.setPassword(user.getPassword());
        entityManager.persist(userToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void setAdminRole(UserDTO user) {
        user.setRole(entityManager.find(Role.class, 1));
    }

    @Override
    public void setUserRole(UserDTO user) {
        user.setRole(entityManager.find(Role.class, 2));
    }
}
