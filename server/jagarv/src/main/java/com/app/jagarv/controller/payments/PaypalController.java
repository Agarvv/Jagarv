package com.app.jagarv.controller.payments;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.jagarv.service.payments.PaypalService;
import com.paypal.base.rest.PayPalRESTException;

import com.app.jagarv.exception.exceptions.payments.PaymentException; 

@RestController
@RequestMapping("/api/jagarv/pay/paypal")
public class PaypalController 
{
    private final PaypalService paypalService; 

    public PaypalController(PaypalService paypalService) 
    {
        this.paypalService = paypalService;
    }
    
    // paypal payment endpoint
    @PostMapping
    public ResponseEntity<String> handleCartPayment
    () 
    {
        try {
            String redirectUrl = paypalService.createPayment();
            return ResponseEntity.ok(redirectUrl); 
        } catch (PayPalRESTException e) {
            throw new PaymentException(e); 
        }
    }
    
    // success paypal payment endpoint 
    @GetMapping("/success")
    public ResponseEntity<String>
    handleSuccess
    (
     @RequestParam("paymentId") String paymentId,
     @RequestParam("PayerID") String payerId
    ) 
    {
        try 
        {
            String message = paypalService.completePayment(paymentId, payerId);
            return ResponseEntity.ok(message);
        } catch (PayPalRESTException e) {
            throw new PaymentException(e); 
        }
    }
}