package com.app.jagarv.filter;

import com.app.jagarv.service.security.JwtTokenService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

import com.app.jagarv.outil.JwtOutil; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component 
@WebFilter("/*")
public class AuthFilter extends OncePerRequestFilter 
{
    private final JwtOutil jwtOutil; 
    
    public AuthFilter(JwtOutil jwtOutil) 
    {
        this.jwtOutil = jwtOutil;
    }
    
    @Override 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException 
    {
        Cookie[] cookies = request.getCookies();
        String jwtToken = null; 
        
        if (cookies != null) 
        {
            for (Cookie cookie : cookies) 
            {
                if (cookie.getName().equals("jwt")) 
                {
                    jwtToken = cookie.getValue();
                }
            }
        }
        
        if (jwtToken != null) 
        {
            if (jwtOutil.validateToken(jwtToken)) 
            {
                
                Long userId = jwtOutil.extractUserId(jwtToken);
                String role = jwtOutil.extractRole(jwtToken);

                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null, 
                        Collections.singletonList(new SimpleGrantedAuthority(role)));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response); 
    }
}