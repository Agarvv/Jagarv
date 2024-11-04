package com.app.jagarv.exception.handlers.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.users.InvalidResetTokenOrEmail;

@ControllerAdvice
public class InvalidResetTokenHandler {
    @ExceptionHandler(InvalidResetTokenOrEmail.class) 
    public ResponseEntity<String> handleInvalidResetTokenOrEmail(InvalidResetTokenOrEmail e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
    }
}
