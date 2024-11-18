package com.app.jagarv.controller.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.service.payments.PaypalService;
import com.app.jagarv.service.payments.StripeService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;

import jakarta.servlet.http.HttpServletRequest;

import com.app.jagarv.dto.payments.ProductPaymentDTO;

@RestController
@RequestMapping("/api/jagarv/pay")
public class PaymentsController {

    private final PaypalService paypalService;
    private final StripeService stripeService;

    public PaymentsController(PaypalService paypalService, StripeService stripeService) {
        this.paypalService = paypalService;
        this.stripeService = stripeService;
    }

    @PostMapping("/paypal")
    public String payWithPaypal() {
        return "";
    }

    @PostMapping("/stripe")
    public ResponseEntity<String> payWithStripe(@RequestBody ProductPaymentDTO payment) {
        try {
            String clientSecret = stripeService.createPaymentIntent(payment);
            return ResponseEntity.ok(clientSecret); 
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)  
                .body("Something went wrong with the payment..." + e.getMessage());
        }
    }

    @PostMapping("/stripe/webhook")
    public ResponseEntity<String> handleStripeWebhook(HttpServletRequest request) {
        try {
            stripeService.handleStripeWebhook(request); 
            return ResponseEntity.ok("Payment Succesfully Processed");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Something went wrong with your payment.." + e.getMessage());
        }
    }
}
