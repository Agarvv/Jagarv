package com.app.jagarv.service.payments;

import org.springframework.stereotype.Service;
import com.app.jagarv.dto.payments.ProductPaymentDTO;
import com.stripe.model.Charge;
import com.stripe.exception.StripeException;
import com.stripe.Stripe;
import com.stripe.model.Token;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.entity.product.Product;  
import com.app.jagarv.outil.ProductOutil; 
import com.app.jagarv.outil.PaymentOutil; 

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StripeService {

    private final ProductRepository productRepository;
    private final ProductOutil productOutil; 
    private final PaymentOutil paymentOutil;

    public StripeService(ProductRepository productRepository, ProductOutil productOutil, PaymentOutil paymentOutil) {
        this.productRepository = productRepository;
        this.productOutil = productOutil;
        this.paymentOutil = paymentOutil;
    }

    public void payProducts(ProductPaymentDTO payment) {

        Map<Long, Product> productsMap = productOutil.findAllById(payment.getProductIds());
        
        Long finalPrice = paymentOutil.calculateProductsPrice(productsMap) * 100; // converted to cents
    
        
        try {
            Charge charge = Charge.create(
                Map.of(
                    "amount", finalPrice, 
                    "currency", "usd", 
                    "source", "tokn,", 
                    "description", "Products Payment at Jagarv"
                )
            );
            
        } catch (StripeException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
}