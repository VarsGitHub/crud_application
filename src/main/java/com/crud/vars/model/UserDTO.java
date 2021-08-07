package com.crud.vars.model;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private String name;
    private String password;
    private String mail;
    private boolean admin;
    private Set<Role> roles = new HashSet<>();

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRole(Role role) {
        roles.add(role);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public boolean getAdmin(){
        return admin;
    }

    public void setAdmin(boolean admin){
        this.admin = admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
