package com.example.sleeter.controller;

import com.example.sleeter.dto.LoginRequest;
import com.example.sleeter.dto.LoginResponse;
import com.example.sleeter.dto.RegistrationRequest;
import com.example.sleeter.persistence.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping(value = "/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping(value = "/signup")
    public LoginResponse register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return userService.register(registrationRequest);
    }
}
