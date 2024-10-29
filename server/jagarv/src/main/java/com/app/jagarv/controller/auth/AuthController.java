package com.app.jagarv.controller.Auth;

import com.app.jagarv.dto.user.RegisterUserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// This controller handles all the auth logic,
// like Login, register, or reset password.
// Google, Github, and Twitter auth will be handled on another file of the Auth package.

@RestController
@RequestMapping("/api/jagarv/auth")
public class AuthController {
    @GetMapping("/register") 
    public ResponseEntity<String> registerUser() {
        
    }
}

