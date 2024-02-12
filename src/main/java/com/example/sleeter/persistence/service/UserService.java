package com.example.sleeter.persistence.service;

import com.example.sleeter.dto.LoginRequest;
import com.example.sleeter.dto.LoginResponse;
import com.example.sleeter.dto.RegistrationRequest;
import com.example.sleeter.persistence.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> createUser(User user);

    User checkByLoginAndPassword(LoginRequest loginRequest);

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse register(RegistrationRequest registrationRequest);
}
