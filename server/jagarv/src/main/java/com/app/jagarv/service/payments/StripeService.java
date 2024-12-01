package com.app.jagarv.service.payments;

import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.app.jagarv.outil.PaymentOutil;
import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.exception.exceptions.payments.PaymentException;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.repository.cart.CartRepository;
import com.app.jagarv.service.admin.order.AdminOrdersService;
import com.app.jagarv.service.cart.CartService;
import com.app.jagarv.dto.payments.ProductPaymentDTO;

@Service
public class StripeService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final AdminOrdersService adminOrdersService;
    private final CartService cartService;

    @Value("${stripe.secret}")
    private String stripeApiKey;

    @Value("${stripe.webhook_secret}")
    private String webhookSecret;

    public StripeService(
        ProductRepository productRepository,
        CartRepository cartRepository,
        AdminOrdersService adminOrdersService,
        CartService cartService
    ) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.adminOrdersService = adminOrdersService;
        this.cartService = cartService;
    }

    public String createCheckoutSession(ProductPaymentDTO payment) throws StripeException {
        Cart cart = cartService.getUserRawCart();
        BigDecimal totalPrice = PaymentOutil.calculateCartTotalPrice(cart);

        long amountInCents = totalPrice.multiply(BigDecimal.valueOf(100)).longValueExact();

        Stripe.apiKey = stripeApiKey;

        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
            .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount(amountInCents)
                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                    .setName("Jagarv Products")
                    .build())
                .build())
            .build();

        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
    .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
        .setCurrency("usd")
        .setUnitAmount(amountInCents)
        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
            .setName("Jagarv Products")
            .build())
        .build())
    .setQuantity(1) // just for now
    .build();
    
    
        Session session = Session.create(params);

        return session.getUrl();
    }

    public void handleStripeWebhook(HttpServletRequest request) throws SignatureVerificationException {
        try {
            String payload = new String(request.getInputStream().readAllBytes());
            String sigHeader = request.getHeader("Stripe-Signature");

            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);

            switch (event.getType()) {
                case "checkout.session.completed":
                    handleCheckoutSessionCompleted(event);  
                    break;
                default:
                    throw new PaymentException("Unhandled event type: " + event.getType());
            }
        } catch (Exception ex) {
            throw new PaymentException("Something went wrong with your payment: ");
        }
    }

    private void handleCheckoutSessionCompleted(Event event) {
        Session session = (Session) event.getData().getObject();
        

        String sessionId = session.getId();
        Long amountReceived = session.getAmountTotal();
        String paymentIntentId = session.getPaymentIntent(); 
        
        adminOrdersService.placeOrder(amountReceived, paymentIntentId, "STRIPE");
    }
    
}
