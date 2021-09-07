package com.vars.spring_boot.dao;

import com.vars.spring_boot.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(value = "User.roles")
    User findUserByMail(String s);

}
