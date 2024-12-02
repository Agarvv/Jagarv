package com.app.jagarv.service.payments;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.entity.user.User; 
import com.app.jagarv.outil.PaymentOutil;
import com.app.jagarv.service.cart.CartService;
import com.app.jagarv.dto.payments.ProductPaymentDTO;
import com.app.jagarv.service.user.UserService;
import java.math.BigDecimal;
import com.app.jagarv.service.admin.order.OrderService; 

@Service
public class StripeService {

    private final CartService cartService;
    private final UserService userService;
    private final OrderService orderService; 

    @Value("${stripe.secret}")
    private String stripeApiKey;

    public StripeService(CartService cartService, UserService userService, OrderService orderService) {
        this.cartService = cartService;
        this.userService = userService;
        Stripe.apiKey = stripeApiKey;
        this.orderService = orderService; 
    }

    // Crear la sesión de pago en Stripe
    public String createCheckoutSession(ProductPaymentDTO payment) throws StripeException {
        User user = userService.findAuthenticatedUser(); 

        Cart cart = cartService.getUserRawCart();
        BigDecimal totalPrice = PaymentOutil.calculateCartTotalPrice(cart);

        long amountInCents = totalPrice.multiply(BigDecimal.valueOf(100)).longValueExact();

        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
            .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount(amountInCents)
                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                    .setName("Jagarv Products")
                    .build())
                .build())
            .setQuantity(1L)  // Se podría mejorar con más detalles
            .build();

        SessionCreateParams params = SessionCreateParams.builder()
            .addLineItem(lineItem)
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("https://jagarv-jq5o.onrender.com/api/jagarv/pay/stripe/success?session_id={CHECKOUT_SESSION_ID}")
            .setCancelUrl("https://jagarv.vercel.app/cancelPayment")
            .putMetadata("userId", user.getId().toString())
            .build();

        Session session = Session.create(params);
        return session.getUrl();
    }

    // Verificar el estado del pago y retornar un mensaje limpio
    public ApiResponse<String> verifyPaymentStatus(String sessionId) throws StripeException {
        try {
            Session session = Session.retrieve(sessionId);
            String paymentStatus = session.getPaymentStatus();
            
            if ("succeeded".equals(paymentStatus)) {
                String paymentIntentId = session.getPaymentIntent(); 
                
                PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
                Long amountReceived = paymentIntent.getAmountReceived();
                
                public void placeOrder(Long amount, String paymentIntentId, String method, Long userId) {
      
      
                orderService.placeOrder(amountReceived, paymentIntentId, "Stripe")

                String message = String.format("Payment succeeded. Payment Intent ID: %s, Amount Received: $%.2f", 
                                               paymentIntentId, amountReceived / 100.0);
                return new ApiResponse<>("Payment Status", message);
            } else {
                return new ApiResponse<>("Payment Failed", "Payment did not succeed. Please try again.");
            }
        } catch (StripeException e) {
            throw new StripeException("Error occurred while verifying payment status: " + e.getMessage());
        }
    }
}