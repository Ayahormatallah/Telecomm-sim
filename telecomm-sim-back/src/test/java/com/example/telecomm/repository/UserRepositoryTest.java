package com.example.telecomm.repository;

import com.example.telecomm.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserRepository() {
        // Créer un utilisateur
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        //  user.setRole("Etudiant");

        // Sauvegarder l'utilisateur
        userRepository.save(user);

        // Récupérer l'utilisateur sauvegardé
        User savedUser = userRepository.findByUsername("testuser");

        // Assertions
        assertNotNull(savedUser);
        System.out.println("Utilisateur trouvé : " + savedUser.getUsername());
    }
}
