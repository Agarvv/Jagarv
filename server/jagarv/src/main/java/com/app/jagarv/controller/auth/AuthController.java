package com.app.jagarv.controller.auth;

import com.app.jagarv.dto.user.RegisterUserDTO;
import com.app.jagarv.service.auth.AuthService;
import com.app.jagarv.dto.user.LoginUserDTO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

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
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDTO loginUserDTO, HttpServletResponse response) {

            String res = authService.loginUser(loginUserDTO, response);
            return ResponseEntity.ok(res);
     
    }
    
    @PostMapping("/send_reset_code")
    public ResponseEntity<String> sendResetCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String response = authService.sendResetCode(email);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/reset_password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String token = request.get("token");
        String newPassword = request.get("password");
        
        String res = authService.resetPassword(newPassword, email, token);
        
        return ResponseEntity.ok(res);
    }
    
    @GetMapping("/check")
    public ResponseEntity<String> checkUserAuthenticated(@CookieValue(value="jwt", required = false) String jwtToken) {
        
        authService.checkIfAuthenticated(jwtToken);
        
        return ResponseEntity.ok("You are authenticated");
        
    }
}

