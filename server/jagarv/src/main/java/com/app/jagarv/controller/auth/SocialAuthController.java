package com.app.jagarv.controller.auth;

import com.app.jagarv.service.auth.SocialAuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse; 
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.dto.user.GoogleAuthDTO;


// handles social media auth
@RestController
@RequestMapping("/api/jagarv/auth")
public class SocialAuthController {

    private final SocialAuthService socialAuthService;
    public SocialAuthController(SocialAuthService socialAuthService) {
        this.socialAuthService = socialAuthService;
    }
    
    // google auth endpoint
    @PostMapping("/google")
    public ResponseEntity<ApiResponse<Void>> handleLoginGoogle(@RequestBody GoogleAuthDTO googleAuthDTO, HttpServletResponse response) {
        Cookie jwtCookie = socialAuthService.verifyToken(googleAuthDTO.getGoogleAuthToken());
        response.addCookie(jwtCookie);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", null));
        // that success message will not be displayed on the frontend, is just for debugging finals
    }
}