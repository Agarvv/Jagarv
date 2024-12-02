package com.app.jagarv.controller.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.dto.payments.ProductPaymentDTO;
import com.stripe.exception.StripeException;
import com.app.jagarv.service.payments.StripeService;
import com.app.jagarv.outil.SendMail;
import com.app.jagarv.dto.ApiResponse; 

@RestController
@RequestMapping("/api/jagarv/pay/stripe")
public class StripeController {

    private final StripeService stripeService; 
    private final SendMail sendMail;

    public StripeController(StripeService stripeService, SendMail sendMail) {
        this.stripeService = stripeService;
        this.sendMail = sendMail;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> handleCartPayment(@RequestBody ProductPaymentDTO payment) {
        try {
            String checkoutSessionUrl = stripeService.createCheckoutSession(payment);
            return ResponseEntity.ok(new ApiResponse<>("url", checkoutSessionUrl));
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)  
                .body(new ApiResponse<>("Something went wrong with the payment...", e.getMessage()));
        }
    }

    @GetMapping("/success")
    public ResponseEntity<ApiResponse<String>> handleSuccess(@RequestParam String session_id) {
        try {
            ApiResponse<String> response = stripeService.verifyPaymentStatus(session_id);
            return ResponseEntity.ok(response); 
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Error In Payment...", e.getMessage()));
        }
    }
}