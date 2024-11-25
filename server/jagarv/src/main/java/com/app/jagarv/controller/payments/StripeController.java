package com.app.jagarv.controller.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.dto.payments.ProductPaymentDTO;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.app.jagarv.service.payments.StripeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/jagarv/pay/stripe")
public class StripeController 
{
    private final StripeService stripeService; 

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping
    public ResponseEntity<String> handleCartPayment(@RequestBody ProductPaymentDTO payment) {
        try {
            String checkoutSessionUrl = stripeService.createCheckoutSession(payment);
            return ResponseEntity.ok(checkoutSessionUrl); 
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)  
                .body("Something went wrong with the payment..." + e.getMessage());
        }
    }

    @PostMapping("/stripe/webhook")
    public ResponseEntity<String> handleStripeWebhook(HttpServletRequest request) {
        try {
            stripeService.handleStripeWebhook(request); 
            return ResponseEntity.ok("Payment Successfully Processed");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Something went wrong with your payment.." + e.getMessage());
        }
    }
}
