package com.app.jagarv.service.payments;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.app.jagarv.outil.PaymentOutil;
import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.exception.exceptions.payments.PaymentException;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.repository.cart.CartRepository;
import com.app.jagarv.outil.SecurityOutil;
import com.stripe.net.Webhook;
import com.stripe.model.Event;
import com.app.jagarv.service.admin.order.AdminOrdersService;
import com.app.jagarv.service.cart.CartService;
import com.app.jagarv.exception.exceptions.payments.PaymentException;

@Service
public class StripeService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final SecurityOutil securityOutil;
    private final AdminOrdersService adminOrdersService;
    private final CartService cartService;

    @Value("${stripe.publicKey}")
    private String stripeApiKey;

    @Value("${stripe.webhook_secret}")
    private String webhookSecret;

    public StripeService(
        ProductRepository productRepository,
        CartRepository cartRepository,
        SecurityOutil securityOutil,
        AdminOrdersService adminOrdersService,
        CartService cartService 
    ) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.securityOutil = securityOutil;
        this.adminOrdersService = adminOrdersService;
        this.cartService = cartService;
    }

    public String createPaymentIntent() throws StripeException {
    
        Cart cart = cartService.getUserRawCart();
        BigDecimal finalPrice = PaymentOutil.calculateCartTotalPrice(cart);
    
        long amountInCents = finalPrice.multiply(BigDecimal.valueOf(100)).longValueExact();
    
        Stripe.apiKey = stripeApiKey;
    
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(amountInCents)
            .setCurrency("usd")
            .setDescription("Jagarv payment")
            .build();
    
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent.getClientSecret();
    }
    
    public void handleStripeWebhook(HttpServletRequest request) throws SignatureVerificationException {
        try {
            String payload = new String(request.getInputStream().readAllBytes());
            String sigHeader = request.getHeader("Stripe-Signature");

            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);

            switch (event.getType()) {
                case "payment_intent.succeeded":
                    handlePaymentSucceeded(event);
                    break;
                case "payment_intent.payment_failed":
                    handlePaymentFailed(event);
                    break;
               
            }
        } catch (Exception ex) {
            throw new PaymentException("Something Went Wrong with your payment");
        }
    }

    private void handlePaymentSucceeded(Event event) {
        PaymentIntent paymentIntent = (PaymentIntent) event.getData().getObject();
        Long amountReceived = paymentIntent.getAmountReceived();
        String paymentIntentId = paymentIntent.getId();

        adminOrdersService.placeOrder(amountReceived, paymentIntentId);
    }

    private void handlePaymentFailed(Event event) {
       throw new PaymentException("Something went wrong with your payment");
    }
}
