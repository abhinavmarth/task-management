package com.cloud.task_management.service;

import com.cloud.task_management.auth.LoginRequest;
import com.cloud.task_management.auth.RegisterRequest;
import com.cloud.task_management.model.User;
import com.cloud.task_management.repository.UserRepository;
import com.cloud.task_management.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepo,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authManager,
                       JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest req) {
        if (userRepo.findByUsername(req.username()).isPresent()) {
            System.out.println("Username already taken");
            throw new IllegalArgumentException("Username already taken");
        }
        User user = User.builder()
                .username(req.username())
                .email(req.email())
                .password(passwordEncoder.encode(req.password()))
                .build();
        userRepo.save(user);
    }

    public String login(LoginRequest req) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        return jwtService.generateToken(req.username());
    }
}
