package com.app.jagarv.exceptions.handlers.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.users.UserBannedException; 

@ControllerAdvice
public class UserBannedHandler {
    @ExceptionHandler(UserBannedException.class) {
        public ResponseEntity<String>
        handleUserBannedException(UserBannedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}