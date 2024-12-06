package com.app.jagarv.controller.payments;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.jagarv.service.payments.PaypalService;
import com.paypal.base.rest.PayPalRESTException;
import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.exception.exceptions.payments.PaymentException; 
import com.app.jagarv.dto.payments.ProductPaymentDTO;

import jakarta.servlet.http.HttpServletResponse;

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
    public ResponseEntity<ApiResponse<String>> handleCartPayment
    (@RequestBody ProductPaymentDTO productPaymentDTO) 
    {
        try {
            String redirectUrl = paypalService.createPayment(productPaymentDTO.getDiscountCode());
            return ResponseEntity.ok(new ApiResponse<>("url", redirectUrl)); 
        } catch (PayPalRESTException e) {
            throw new PaymentException(e.getMessage()); 
        }
    }
    
    // success paypal payment endpoint 
    @GetMapping("/success")
      public void handleSuccess(@RequestParam("paymentId") String paymentId, 
                          @RequestParam("PayerID") String payerId, 
                          HttpServletResponse response) throws IOException {
    try {
        paypalService.completePayment(paymentId, payerId);
        response.sendRedirect("https://jagarv.vercel.app/paymentSuccess");
    } catch (PayPalRESTException e) {
        response.sendRedirect("https://jagarv.vercel.app/paymentFailure");
    }
}


}