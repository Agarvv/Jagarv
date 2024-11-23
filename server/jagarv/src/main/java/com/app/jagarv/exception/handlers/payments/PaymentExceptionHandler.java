package com.app.jagarv.exception.handlers.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.jagarv.exception.exceptions.payments.PaymentException;

@ControllerAdvice 
public class PaymentExceptionHandler {
     @ExceptionHandler(PaymentException.class) 
     public ResponseEntity<String> 
     handlePaymentException(PaymentException e) {
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
     }
}
