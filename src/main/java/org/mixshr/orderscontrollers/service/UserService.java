package org.mixshr.orderscontrollers.service;

import lombok.AllArgsConstructor;
import org.mixshr.orderscontrollers.entity.UserEntity;
import org.mixshr.orderscontrollers.enums.UserRole;
import org.mixshr.orderscontrollers.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity create(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::findByUsername;
    }

    public UserEntity getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
    }

    @Deprecated
    public void setAdminRole() {
        UserEntity user = getCurrentUser();
        user.setRole(UserRole.ROLE_ADMIN);
        userRepository.save(user);
    }
}