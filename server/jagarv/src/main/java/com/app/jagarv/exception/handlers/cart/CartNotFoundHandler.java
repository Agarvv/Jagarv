package com.app.jagarv.exception.handlers.cart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.cart.CartNotFoundException;

@ControllerAdvice 
public class CartNotFoundHandler {
    @ExceptionHandler(CartNotFoundException.class) 
        public ResponseEntity<String> handleCartNotFoundException(CartNotFoundException e) {
            return ResponseEntity.status
            (HttpStatus.NOT_FOUND).body
            (e.getMessage());
        
        }
}
