package org.mixshr.orderscontrollers.controller;

import lombok.AllArgsConstructor;
import org.mixshr.orderscontrollers.dto.JwtAuthenticationDTO;
import org.mixshr.orderscontrollers.dto.SignInDTO;
import org.mixshr.orderscontrollers.dto.SignUpDTO;
import org.mixshr.orderscontrollers.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationDTO> signIn(
            @RequestBody SignInDTO signInDTO
    ) {
        return ResponseEntity.ok(authenticationService.signIn(signInDTO));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationDTO> signUp(
            @RequestBody SignUpDTO signUpDTO
    ) {
        return ResponseEntity.ok(authenticationService.signUp(signUpDTO));
    }
}