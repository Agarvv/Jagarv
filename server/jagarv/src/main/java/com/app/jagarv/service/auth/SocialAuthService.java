package com.app.jagarv.service.auth;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import jakarta.servlet.http.Cookie;



@Service
public class SocialAuthService {
      
    @Autowired private AuthService jagarvAuthService;


    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private final GoogleIdTokenVerifier verifier;

    public SocialAuthService(@Value("${google.clientId}") String googleClientId) {
        this.verifier = new GoogleIdTokenVerifier.Builder(
            new NetHttpTransport(), JSON_FACTORY)
                .setAudience(Collections.singletonList(googleClientId))
                .build();
    }

    public Cookie verifyToken(String idTokenString) {
        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                Cookie jwtCookie = jagarvAuthService.loginWithSocialMedia(payload);
                return jwtCookie;
            } else { 
                return null;
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
