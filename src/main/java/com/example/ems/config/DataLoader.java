package com.example.ems.config;

import com.example.ems.model.User;
import com.example.ems.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class DataLoader {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // This method will be executed when the application starts
    @Bean
    public CommandLineRunner createDefaultUsers() {
        return args -> {
            if (userRepository.findAll().isEmpty()) {
                // Create and save Admin user
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin123"));
                adminUser.setRole("ADMIN");
                userRepository.save(adminUser);

                // Create and save Employee user
                User employeeUser = new User();
                employeeUser.setUsername("employee");
                employeeUser.setPassword(passwordEncoder.encode("employee123"));
                employeeUser.setRole("EMPLOYEE");
                userRepository.save(employeeUser);

                System.out.println("Default users (Admin and Employee) created successfully.");
            } 
            else {
                System.out.println("Users already exist in the database.");
            }
        };
    }
}
