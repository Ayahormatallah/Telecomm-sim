package com.example.telecomm;

import com.example.telecomm.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Bean pour encoder les mots de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Configuration de la sécurité HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactivation temporaire de CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/about", "/css/**", "/js/**", "/images/**", "/signup").permitAll() // Accessible à tous
                        .requestMatchers("/simulate/**").authenticated() // Nécessite une authentification
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Page de connexion personnalisée
                        .defaultSuccessUrl("/simulate", true) // Redirection après succès
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // Redirection après déconnexion
                        .permitAll()
                );
        return http.build();
    }

    // Bean pour gérer l'authentification
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
