package com.newProject.mvc.Configurations;

import com.newProject.mvc.Entities.UserAccount;
import com.newProject.mvc.Repsitories.UserManagementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

// By default, Spring Security requires a user (username + password) → we’re overriding that by defining our own UserDetailsService
@Configuration //Tells Spring: “This class provides bean definitions.”
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){ //Core Spring Security interface that defines where user info comes from
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(); //A simple in-memory user store (good for demos, tests, or small apps).
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder() //Used here for simplicity → stores passwords in plain text (unsafe for production).
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build());
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build());
        return userDetailsManager;
    }

    @Bean
    CommandLineRunner initUsers(UserManagementRepository repository) {
        return args -> {
            repository.save(new UserAccount("user", "password", "ROLE_USER"));
            repository.save(new UserAccount("admin", "password", "ROLE_ADMIN"));
        };
    }
}
//Here, two users are created: "user" with role "ROLE_USER"."admin" with role "ROLE_ADMIN".
//UserManagementRepository Handles saving and fetching UserAccount entities.
//On application startup, this code preloads two users into the database.
//That way, you already have accounts to log in with during testing.
//Later, you can plug this into Spring Security’s UserDetailsService to authenticate users from the DB.
