package com.app.jagarv.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
   
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") 
                .allowedOrigins("https://jagarv.vercel.app/") 
                .allowedMethods("GET", "POST") 
                .allowedHeaders("*") 
                .allowCredentials(true); 
    }
}
