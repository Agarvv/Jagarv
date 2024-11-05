package com.app.jagarv.controller.auth;

import com.app.jagarv.service.auth.SocialAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

@RestController
@RequestMapping("/api/jagarv/auth")
public class SocialAuthController {

    @Autowired
    private SocialAuthService socialAuthService;

    @PostMapping("/google")
    public ResponseEntity<String> handleLoginGoogle(@RequestBody String googleAuthToken, HttpServletResponse response) {
        Cookie jwtCookie = socialAuthService.verifyToken(googleAuthToken);
        response.addCookie(jwtCookie);
        
        // im gonna handle responses and errors when all the auth system is finished, for now im gonna just let it like this just for debugging.
        return ResponseEntity.ok("Ok");
    }
}