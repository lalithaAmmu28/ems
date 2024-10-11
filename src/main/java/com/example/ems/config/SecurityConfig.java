package com.example.ems.config;

import com.example.ems.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (ensure to handle CSRF if needed)
            .authorizeHttpRequests(authorize -> authorize
                // Allow public access to the login page, error page, and static resources
                .requestMatchers("/login", "/error").permitAll()
                .requestMatchers( "/forgot-password/**", "/process-forgot-password", "/css/**", "/images/**").permitAll() // Allow access to CSS and images

                // Ensure that other resources are protected based on roles
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/employee/**").hasRole("EMPLOYEE")
                
                // Any other request must be authenticated
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")  // Custom login page
                .permitAll()  // Ensure everyone can access the login page
                .defaultSuccessUrl("/dashboard", true)  // Redirect to dashboard after successful login
            )
            .logout(LogoutConfigurer::permitAll);  // Allow everyone to access the logout functionality

        return http.build();
    }

    public UserDetailsService userDetailsService() {
        return userService::loadUserByUsername;
    }
}
