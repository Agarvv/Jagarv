package com.app.jagarv.service.payments;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCreateParams;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.jagarv.dto.payments.ProductPaymentDTO;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.outil.PaymentOutil;

@Service
public class StripeService {
    private final ProductRepository productRepository;

    @Value("${stripe.publicKey}")
    private String stripeApiKey;

    public StripeService
    (
        ProductRepository productRepository
    ) 
    {
      this.productRepository = productRepository;
    }

    public String createPaymentIntent(ProductPaymentDTO payment) {
        try {
     
            List<Product> products = productRepository.findAllById(payment.getProductIds());
    
        
            Long finalPrice = PaymentOutil.calculateProductsPrice(products) * 100;
    
            
            Stripe.apiKey = stripeApiKey;
    
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(finalPrice) 
                .setCurrency("usd")
                .setDescription("Jagarv payment")
                .build();
    
            PaymentIntent paymentIntent = PaymentIntent.create(params);
    
            return paymentIntent.getClientSecret();
    
        } catch (StripeException e) {
            throw new RuntimeException("Payment error stripe" + e.getMessage(), e);
            // just for now as debug
        }
    }

}

