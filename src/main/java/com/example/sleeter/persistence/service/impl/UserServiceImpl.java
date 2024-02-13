package com.example.sleeter.persistence.service.impl;


import com.example.sleeter.dto.LoginRequest;
import com.example.sleeter.dto.LoginResponse;
import com.example.sleeter.dto.RegistrationRequest;
import com.example.sleeter.exception.AuthException;
import com.example.sleeter.persistence.entity.User;
import com.example.sleeter.persistence.repository.UserRepository;
import com.example.sleeter.persistence.service.UserService;
import com.example.sleeter.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCrypt;
    //    private final EmailServiceImpl emailService;
    private final JwtUtil jwtUtil;

    @Override
    public Optional<User> createUser(User user) {
        User newUser = userRepository.save(user);
        userRepository.flush();
        return Optional.of(newUser);
    }

    @Override
    public User checkByLoginAndPassword(LoginRequest loginRequest) {
        User user = userRepository.findUserByLoginIgnoreCase(loginRequest.login())
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email"));
        if (bCrypt.matches(loginRequest.password(), user.getPassword())) {
            return user;
        }
        throw new UsernameNotFoundException("Invalid password");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            User user = checkByLoginAndPassword(loginRequest);
            String token = jwtUtil.createToken(user);
            return new LoginResponse(token);
        } catch (BadCredentialsException e) {
            throw new AuthException("Registration Error", "User is not registered", "Invalid username or password");
        } catch (Exception e) {
            throw new AuthException("Registration Error", "User is not registered", e.getMessage());
        }
    }

    @Override
    public LoginResponse register(RegistrationRequest registrationRequest) {
        User user = User.builder()
                .login(registrationRequest.login())
                .password(bCryptPasswordEncoder.encode(registrationRequest.password()))
                .isVerified(false)
                .build();

        User newUser = createUser(user).orElseThrow(
                () -> new AuthException("Registration Error", "Invalid user data", "Failed to create a new user in the database")
        );
//        emailService.sendSimpleMessage(newUser);

        String token = jwtUtil.createToken(newUser);
        return new LoginResponse(token);
    }
}
