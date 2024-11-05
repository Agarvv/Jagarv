package com.app.jagarv.controller.auth;

import com.app.jagarv.dto.user.RegisterUserDTO;
import com.app.jagarv.service.auth.AuthService;
import com.app.jagarv.dto.ApiResponse;
import jakarta.validation.Valid;
import com.app.jagarv.dto.user.LoginUserDTO;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

// This controller handles all the auth logic,
// like Login, register, or reset password.
// Google, Github, and Twitter auth will be handled in another file of the Auth package.

@RestController
@RequestMapping("/api/jagarv/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> registerUser(@Valid @RequestBody RegisterUserDTO user) {
        authService.registerUser(user);
        return ResponseEntity.ok(new ApiResponse<>("Welcome To Jagarv!", null));
    }

    // Login endpoint 
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@Valid @RequestBody LoginUserDTO loginUserDTO, HttpServletResponse response) {
        Cookie jwtCookie = authService.loginUser(loginUserDTO);
        response.addCookie(jwtCookie); 
        
        return ResponseEntity.ok(new ApiResponse<>("Welcome Back!", jwtCookie.getValue()));
    }
    
    @PostMapping("/send_reset_code")
    public ResponseEntity<ApiResponse<Void>> sendResetCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        authService.sendResetCode(email);
        return ResponseEntity.ok(new ApiResponse<Void>("Check Your Email!", null));
    }
    
    @PostMapping("/reset_password")
    public ResponseEntity<ApiResponse<Void>> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String token = request.get("token");
        String newPassword = request.get("password");
        
        authService.resetPassword(newPassword, email, token);
        
        return ResponseEntity.ok(new ApiResponse<Void>("Your Password Has Been Reset!", null));
    }
    
    @GetMapping("/check")
    public ResponseEntity<ApiResponse<Void>> checkUserAuthenticated(@CookieValue(value="jwt", required = false) String jwtToken) {
        authService.checkIfAuthenticated(jwtToken);
        return ResponseEntity.ok(new ApiResponse<Void>("AUTHENTICATED", null)); 
        // that message will be not displayed on frontend
    }
}