package com.app.jagarv.controller.auth;

import com.app.jagarv.dto.user.RegisterUserDTO;
import com.app.jagarv.service.auth.AuthService;
import com.app.jagarv.dto.user.LoginUserDTO; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This controller handles all the auth logic,
// like Login, register, or reset password.
// Google, Github, and Twitter auth will be handled in another file of the Auth package.

@RestController
@RequestMapping("/api/jagarv/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    // Endpoint for user registration
    @PostMapping("/register") 
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO user) {
        authService.registerUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }
    
    // Login endpoint 
    @PostMapping("/login") 
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDTO loginUserDTO) {

            String response = authService.loginUser(loginUserDTO);
            return ResponseEntity.ok(response);
     
    }
}

