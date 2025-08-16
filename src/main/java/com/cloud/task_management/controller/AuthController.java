package com.cloud.task_management.controller;

import com.cloud.task_management.auth.LoginRequest;
import com.cloud.task_management.auth.LoginResponse;
import com.cloud.task_management.auth.RegisterRequest;
import com.cloud.task_management.repository.UserRepository;
import com.cloud.task_management.service.AuthService;
import com.cloud.task_management.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@Valid @RequestBody RegisterRequest req){
        authService.register(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        String token = authService.login(req);
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
