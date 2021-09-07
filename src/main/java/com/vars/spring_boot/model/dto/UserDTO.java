package com.vars.spring_boot.model.dto;

import com.vars.spring_boot.model.Role;
import com.vars.spring_boot.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private Integer id;
    private String name;
    private String password;
    private String mail;
    private boolean admin;
    private Set<RoleDTO> roles;

    public UserDTO() {
    }

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        password = user.getPassword();
        mail = user.getMail();
        roles = user.getRoles().stream().map(x -> new RoleDTO(x.getRole().substring(5))).collect(Collectors.toSet());
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(Role role) {
        roles.add(new RoleDTO(role.getRole()));
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

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != userDTO.id) return false;
        if (admin != userDTO.admin) return false;
        if (!name.equals(userDTO.name)) return false;
        if (!password.equals(userDTO.password)) return false;
        if (!mail.equals(userDTO.mail)) return false;
        return roles.equals(userDTO.roles);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + mail.hashCode();
        result = 31 * result + (admin ? 1 : 0);
        result = 31 * result + roles.hashCode();
        return result;
    }
}
