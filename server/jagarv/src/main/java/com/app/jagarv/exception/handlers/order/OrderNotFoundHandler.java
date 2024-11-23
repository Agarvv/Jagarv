package com.app.jagarv.exception.handlers.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.order.OrderNotFoundException;

@ControllerAdvice 
public class OrderNotFoundHandler {
    @ExceptionHandler(OrderNotFoundException.class) 
    public ResponseEntity<String>
    handleOrderNotFoundException(OrderNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
