package com.app.jagarv.controller.payments;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.service.payments.PaypalService;
import com.app.jagarv.service.payments.StripeService; 
import com.app.jagarv.dto.payments.ProductPaymentDTO;



@RestController
@RequestMapping("/api/jagarv/pay")
public class PaymentsController {

    private final PaypalService paypalService;
    private final StripeService stripeService;

    public PaymentsController
    (
        PaypalService paypalService,
        StripeService stripeService
    ) 
    {
        this.paypalService = paypalService;
        this.stripeService = stripeService;
    } 

    @PostMapping("/paypal")
    public String payWithPaypal() {
        return "";
    }

    @PostMapping("/stripe") 
    public ResponseEntity<String> payWithStripe(@RequestBody ProductPaymentDTO payment) {
        String clientSecret = stripeService.createPaymentIntent(payment);
        return ResponseEntity.ok(clientSecret);
    }
}
