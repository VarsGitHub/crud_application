package com.crud.vars.util;

import com.crud.vars.model.Role;
import com.crud.vars.model.User;
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
        userFloppa.setPassword("floppapass");
        userFloppa.setMail("FloppaBased@russiancat.ru");
        userFloppa.getRoles().add(userRole);
        entityManager.persist(userFloppa);

        User userAdmin = new User();
        userAdmin.setName("1");
        userAdmin.setPassword("1");
        userAdmin.setMail("admin@admin.ru");
        userAdmin.getRoles().add(adminRole);
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