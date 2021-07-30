package com.crud.vars.util;

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
        entityManager.persist(new User("BigFloppa", "FloPPaBased@russiancat.ru"));
        entityManager.persist(new User("Obame", "uspresident@googol.com"));
        entityManager.persist(new User("TrickyDoge", "japanDoge@sibainu.jap"));
    }
}
