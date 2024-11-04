package com.app.jagarv.exception.handlers.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.users.UsernameAlreadyExistsException;

@ControllerAdvice
public class UsernameAlreadyExistsHandler {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAleadyExistsException(UsernameAlreadyExistsException e) {
       return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
