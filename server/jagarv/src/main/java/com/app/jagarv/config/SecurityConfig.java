package com.app.jagarv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// security configuration class
@Configuration
public class SecurityConfig {
    @Bean
    // bcryt to encode passwords
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

