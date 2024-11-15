package com.app.jagarv.controller.payments; 

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.service.payments.StripeService; 
import com.app.jagarv.dto.payments.ProductPaymentDTO; 
import com.app.jagarv.dto.ApiResponse; 


// payments endpoints 
@RestController 
@RequestMapping("/api/jagarv/pay")
public class StripeController {
    private final StripeService stripeService; 
    
    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService; 
    }
    
    // products payment endpoint 
    @PostMapping("/products") 
    public ResponseEntity<ApiResponse<Void>> 
    payProducts
    (@Valid @RequestBody ProductPaymentDTO payment)
    {
        stripeService.payProducts(payment); 
        return ResponseEntity.ok(new ApiResponse<>("Purchase Success", null)); 
    }
    
}