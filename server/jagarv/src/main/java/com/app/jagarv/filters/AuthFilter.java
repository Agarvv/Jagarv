package com.app.jagarv.filters;

import com.app.jagarv.outil.JwtOutil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.app.jagarv.service.admin.ban.BanService;
import com.app.jagarv.exception.exceptions.users.UserBannedException; 


// auth and banned filter
@Component 
@WebFilter("/*")
public class AuthFilter extends OncePerRequestFilter 
{
    private final JwtOutil jwtOutil;
    private final BanService banService; 
    
    public AuthFilter(JwtOutil jwtOutil, BanService banService) 
    {
        this.jwtOutil = jwtOutil;
        this.banService = banService; 
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String jwtToken = null;
    
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                    break; 
                }
            }
        }
    
        if (jwtToken != null) {
            try {
                if (jwtOutil.validateToken(jwtToken)) {
                    Long userId = jwtOutil.extractUserId(jwtToken);
    
                    if (banService.isBanned(userId)) {
                        response.setStatus(451); 
                        response.getWriter().write("BANNED");
                        return;
                    }
    
                    String role = jwtOutil.extractRole(jwtToken);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userId, null,
                                    Collections.singletonList(new SimpleGrantedAuthority(role)));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    response.setStatus(451);
                    response.getWriter().write("Invalid token");
                    return;
                }
            } catch (Exception e) {
                response.setStatus(451);
                response.getWriter().write("Error processing token");
                return;
            }
        }
    
        filterChain.doFilter(request, response); 
    } 

}   