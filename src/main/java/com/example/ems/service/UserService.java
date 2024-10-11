package com.example.ems.service;

import com.example.ems.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Handle Optional<User> properly
        com.example.ems.model.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Map the custom User object to a Spring Security UserDetails object
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());  // Ensure role is prefixed with "ROLE_"
        
        // Use the fully qualified name for Spring Security's User class
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),           // Username from your custom User object
                user.getPassword(),           // Encoded password from your custom User object
                Collections.singletonList(authority)  // A collection of authorities (roles)
        );
    }

    // Additional method to fetch user details if needed (for example: update, find, etc.)
    public com.example.ems.model.User findByUsername(String username) {
        // Handle Optional<User> properly
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public void updateUser(com.example.ems.model.User user) {
        userRepository.save(user);
    }
}
