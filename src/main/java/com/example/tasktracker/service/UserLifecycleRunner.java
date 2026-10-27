package com.example.tasktracker.service;

import com.example.tasktracker.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserLifecycleRunner implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        User user = new User(
            "lab7_user",
            "original@example.com"
        );

        // Transient: created in Java, not yet stored.
        System.out.println("STATE 1 - Transient: User created.");

        // Managed: persist schedules the insert; flush sends it to MySQL.
        entityManager.persist(user);
        entityManager.flush();
        System.out.println("STATE 2 - Managed: User persisted. ID = " + user.getId());

        // Detached: changes are no longer tracked by this EntityManager.
        entityManager.detach(user);
        System.out.println("STATE 3 - Detached: User detached.");

        // This modification must NOT be saved because the entity is detached.
        user.setEmail("changed@example.com");
        System.out.println("Detached email changed to: " + user.getEmail());

        // Reload from the database without merging the detached object.
        User storedUser = entityManager.find(User.class, user.getId());
        System.out.println(
            "DATABASE EMAIL (should remain original@example.com): "
                + storedUser.getEmail()
        );

        System.out.println("Lifecycle demonstration complete.");
    }
}
