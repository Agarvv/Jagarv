package com.app.jagarv.outil;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SecurityOutil {
    // gets the authenticatdd user id 
    public Long getAuthenticatedUserId()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Long) 
        {
            return (Long) authentication.getPrincipal();
        }
        return null;
    }
    
    // gets the authenticated user role
    public String getAuthenticatedUserRole() 
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) 
        {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (authorities != null && !authorities.isEmpty()) 
            {
                return authorities.iterator().next().getAuthority();
            }
        }
        return null;
    }
}