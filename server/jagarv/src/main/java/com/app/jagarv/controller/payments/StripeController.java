package com.app.jagarv.controller.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.dto.payments.ProductPaymentDTO;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.app.jagarv.service.payments.StripeService;
import com.app.jagarv.outil.SendMail;
import com.app.jagarv.dto.ApiResponse; 

import jakarta.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@RestController
@RequestMapping("/api/jagarv/pay/stripe")
public class StripeController 
{
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
            return ResponseEntity.ok(new ApiResponse<>("url", checkoutSessionUrl))
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)  
                .body("Something went wrong with the payment..." + e.getMessage());
        }
    }


     @PostMapping("/webhook")
public ResponseEntity<String> handleStripeWebhook(HttpServletRequest request) {
    try {
        sendMail.sendMail("casluagarv@gmail.com", 
            "RECEIVED WEBHOOK", 
            "Webhook received successfully"
        );
        
        stripeService.handleStripeWebhook(request);
        return ResponseEntity.ok("Payment Successfully Processed");
    } catch (SignatureVerificationException e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        String exceptionAsString = baos.toString();

        sendMail.sendMail("casluagarv@gmail.com", 
            "ERROR AT STRIPE WEBHOOK", 
            "Signature Verification Error: " + exceptionAsString
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Something went wrong with your payment.. " + exceptionAsString);
    } catch (Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        String exceptionAsString = baos.toString();

        sendMail.sendMail("casluagarv@gmail.com", 
            "ERROR AT STRIPE WEBHOOK", 
            "Unexpected error: " + exceptionAsString
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An unexpected error occurred: " + exceptionAsString);
    }
}

}
