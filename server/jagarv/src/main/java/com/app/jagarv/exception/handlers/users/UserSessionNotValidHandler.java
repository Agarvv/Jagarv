package com.app.jagarv.exception.handlers.users;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.users.UserSessionNotValidException;

@ControllerAdvice 
public class UserSessionNotValidHandler {
    @ExceptionHandler(UserSessionNotValidException.class) 
    public ResponseEntity<String> handleUserSessionNotValidException(UserSessionNotValidException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}

