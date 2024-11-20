package com.app.jagarv.controller.payments;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.jagarv.service.payments.PaypalService;

@RestController
@RequestMapping("/api/jagarv/pay/paypal")
public class PaypalController 
{
    private final PaypalService paypalService; 

    public PaypalController(PaypalService paypalService) 
    {
      this.paypalService = paypalService;
    }
    
    // handles payment of single product, not cart
    @PostMapping("/singleProduct")
    public ResponseEntity<String> handleSinglePayment() 
    {
        return ResponseEntity.ok("ok");
    }
    
    // handles payment of cart
    @PostMapping("/cart") 
    public ResponseEntity<String> handleCartPayment() 
    {
        return ResponseEntity.ok("ok");
    }
}
