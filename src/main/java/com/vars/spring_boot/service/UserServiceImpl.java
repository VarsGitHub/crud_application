package com.vars.spring_boot.service;

import com.vars.spring_boot.dao.RoleRepository;
import com.vars.spring_boot.dao.UserRepository;
import com.vars.spring_boot.model.Role;
import com.vars.spring_boot.model.User;
import com.vars.spring_boot.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final int ADMIN_ROLE_ID;
    private final int USER_ROLE_ID;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(@Value("${admin.role.id}") int admin_role_id, @Value("${user.role.id}") int user_role_id,
                           UserRepository userRepository, RoleRepository roleRepository) {
        ADMIN_ROLE_ID = admin_role_id;
        USER_ROLE_ID = user_role_id;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public User show(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public User saveOrUpdate(UserDTO user) {
        User newUser = new User(user);
        setUserRoles(newUser, user.getAdmin());
        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> getUserDTOS() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByMail(s);
    }

    private void setUserRoles(User user, boolean isAdmin) {
        if (isAdmin) {
            Role roleById = roleRepository.findRoleById(ADMIN_ROLE_ID);
            roleById.addUser(user);
            user.addRole(roleById);
        }
        Role roleById = roleRepository.findRoleById(USER_ROLE_ID);
        user.addRole(roleById);
        roleById.addUser(user);
    }
}
