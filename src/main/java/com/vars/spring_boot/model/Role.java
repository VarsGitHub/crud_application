package com.vars.spring_boot.model;

import com.vars.spring_boot.model.dto.RoleDTO;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name="role")
    private String role;

    @ManyToMany (mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public void setRole(String role) {
        this.role = role;
    }

    public Role() {
    }

    public void addUser(User user) {
        users.add(user);
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return role.replaceAll("ROLE_", "");
    }
}