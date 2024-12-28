package com.example.telecomm.controller;

import com.example.telecomm.entity.User;
import com.example.telecomm.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String signupPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("user", new User());
        if (error != null) {
            switch (error) {
                case "exists":
                    model.addAttribute("errorMessage", "Nom d'utilisateur ou email déjà existant. Veuillez en choisir un autre.");
                    break;
                case "empty_field":
                    model.addAttribute("errorMessage", "Tous les champs sont obligatoires. Veuillez les remplir correctement.");
                    break;
                case "password_mismatch":
                    model.addAttribute("errorMessage", "Les mots de passe ne correspondent pas.");
                    break;
                case "password_too_short":
                    model.addAttribute("errorMessage", "Le mot de passe doit contenir au moins 8 caractères.");
                    break;
                default:
                    model.addAttribute("errorMessage", "Une erreur inconnue est survenue.");
            }
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               Model model) {
        // Vérification des champs obligatoires
        if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            return "redirect:/signup?error=empty_field";
        }

        // Vérifier si l'utilisateur ou l'email existe déjà
        if (userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null) {
            return "redirect:/signup?error=exists";
        }

        // Vérification de la correspondance des mots de passe
        if (!password.equals(confirmPassword)) {
            return "redirect:/signup?error=password_mismatch";
        }

        // Validation du mot de passe (longueur minimale de 8 caractères)
        if (password.length() < 8) {
            return "redirect:/signup?error=password_too_short";
        }

        // Sauvegarde de l'utilisateur
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));  // Ici on encode le mot de passe avant de le définir
        userRepository.save(user);

        // Redirection vers la page de connexion avec un succès
        return "redirect:/login?success";
    }

}
