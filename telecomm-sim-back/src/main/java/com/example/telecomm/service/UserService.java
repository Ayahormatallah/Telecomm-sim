package com.example.telecomm.service;

import com.example.telecomm.entity.User;
import com.example.telecomm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injection du PasswordEncoder

    // Méthode pour enregistrer un utilisateur
    public void registerUser(User user) {
        // Encodage du mot de passe avant de l'enregistrer
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Sauvegarde de l'utilisateur dans la base de données
        userRepository.save(user);
    }
}
