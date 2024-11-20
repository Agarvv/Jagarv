package com.app.jagarv.service.payments;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCreateParams;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.jagarv.dto.payments.ProductPaymentDTO;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.outil.PaymentOutil;
import com.stripe.net.Webhook;
import com.stripe.model.Event;


@Service
public class StripeService {
    private final ProductRepository productRepository;

    @Value("${stripe.publicKey}")
    private String stripeApiKey;

    @Value("${stripe.webhook_secret}")
    private String webhookSecret;

    public StripeService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String createPaymentIntent(ProductPaymentDTO payment) throws StripeException {
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
    }

    public void handleStripeWebhook(HttpServletRequest request)
     throws SignatureVerificationException {
        String payload = request.getParameter("payload");
        String sigHeader = request.getHeader("Stripe-Signature");

        Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);

        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) event.getData().getObject();
                Long amountReceived = paymentIntent.getAmountReceived();
                String paymentIntentId = paymentIntent.getId();
          
                break;
            case "payment_intent.payment_failed":

                break;
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }
    }
}
