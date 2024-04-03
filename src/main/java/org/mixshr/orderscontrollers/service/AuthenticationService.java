package org.mixshr.orderscontrollers.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mixshr.orderscontrollers.dto.JwtAuthenticationDTO;
import org.mixshr.orderscontrollers.dto.SignInDTO;
import org.mixshr.orderscontrollers.dto.SignUpDTO;
import org.mixshr.orderscontrollers.entity.UserEntity;
import org.mixshr.orderscontrollers.enums.UserRole;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationDTO signUp(SignUpDTO signUpDTO) {
        UserEntity user = new UserEntity(
                signUpDTO.getUsername(),
                signUpDTO.getEmail(),
                passwordEncoder.encode(signUpDTO.getPassword()),
                UserRole.ROLE_USER
        );

        userService.save(user);

        String token = jwtService.generateToken(user);
        return new JwtAuthenticationDTO(token);
    }

    public JwtAuthenticationDTO signIn(SignInDTO signInDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDTO.getUsername(), signInDTO.getPassword())
        );

        UserDetails user = userService.userDetailsService().loadUserByUsername(signInDTO.getUsername());

        String token = jwtService.generateToken(user);
        return new JwtAuthenticationDTO(token);
    }
}