package com.app.jagarv.exception.handlers.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.users.
UserMustHaveAdressException;




@ControllerAdvice
public class UserMustHaveAdressHandler {
    @ExceptionHandler(UserMustHaveAdressException.class)
    public ResponseEntity<String> 
    handleUserMustHaveAdressException(UserMustHaveAdressException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
    }
}