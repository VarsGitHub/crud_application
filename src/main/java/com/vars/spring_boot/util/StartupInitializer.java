package com.vars.spring_boot.util;

import com.vars.spring_boot.model.Role;
import com.vars.spring_boot.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class StartupInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        entityManager.persist(adminRole);
        Role userRole = new Role();
        userRole.setRole("ROLE_USER");
        entityManager.persist(userRole);

        User userFloppa = new User();
        userFloppa.setName("Floppa");
        userFloppa.setPassword("2");
        userFloppa.setMail("2");
        userFloppa.getRoles().add(userRole);
        entityManager.persist(userFloppa);

        User userAdmin = new User();
        userAdmin.setName("1");
        userAdmin.setPassword("1");
        userAdmin.setMail("1");
        userAdmin.getRoles().add(adminRole);
        userAdmin.getRoles().add(userRole);
        entityManager.persist(userAdmin);

        User userBoss = new User();
        userBoss.setName("Boss");
        userBoss.setPassword("bosspass");
        userBoss.setMail("boss@boss.ru");
        userBoss.getRoles().add(adminRole);
        userBoss.getRoles().add(userRole);
        entityManager.persist(userBoss);
    }
}