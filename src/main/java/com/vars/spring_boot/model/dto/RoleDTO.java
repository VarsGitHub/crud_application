package com.vars.spring_boot.model.dto;

public class RoleDTO {
    private String role;

    public RoleDTO () {
    }

    public RoleDTO (String role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDTO roleDTO = (RoleDTO) o;

        return role.equals(roleDTO.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

    @Override
    public String toString() {
        return role.replaceAll("ROLE_", "");
    }
}
