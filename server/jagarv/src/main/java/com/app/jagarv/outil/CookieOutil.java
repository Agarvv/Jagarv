package com.app.jagarv.outil;

import javax.servlet.http.Cookie;

public class CookieOutil {
    public static Cookie generateJwtCookie(String jwtToken) {
        Cookie cookie = new Cookie("jwt", jwtToken);  
        cookie.setHttpOnly(true); 
        cookie.setSecure(true); 
        cookie.setPath("/"); 
        cookie.setMaxAge(3600); 
        
        return cookie;
    }
}