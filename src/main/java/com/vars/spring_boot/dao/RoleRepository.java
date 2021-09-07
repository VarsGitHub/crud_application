package com.vars.spring_boot.dao;

import com.vars.spring_boot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleById (Integer id);

}
