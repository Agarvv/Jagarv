package com.app.jagarv.service.payments;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.entity.user.User;
import com.app.jagarv.outil.PaymentOutil;
import com.app.jagarv.service.cart.CartService;
import com.app.jagarv.dto.payments.ProductPaymentDTO;
import com.app.jagarv.service.user.UserService;
import com.app.jagarv.dto.ApiResponse;
import com.stripe.model.PaymentIntent;
import com.app.jagarv.service.admin.order.AdminOrdersService;
import com.app.jagarv.exception.exceptions.payments.PaymentException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class StripeService {

    private final CartService cartService;
    private final UserService userService;
    private final AdminOrdersService orderService;

    @Value("${stripe.secret}")
    private String stripeApiKey;

    public StripeService(CartService cartService, UserService userService, AdminOrdersService orderService) {
        this.cartService = cartService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
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
            .setQuantity(1L)
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

    public ApiResponse<String> verifyPaymentStatus(String sessionId) throws StripeException {
        try {
            Session session = Session.retrieve(sessionId);
            String paymentStatus = session.getPaymentStatus();

            if ("paid".equals(paymentStatus)) {
                String paymentIntentId = session.getPaymentIntent();
                PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
                Long amountReceived = paymentIntent.getAmountReceived();

                orderService.placeOrder(amountReceived, paymentIntentId, "Stripe");

                String message = String.format("Payment succeeded. Payment Intent ID: %s, Amount Received: $%.2f",
                        paymentIntentId, amountReceived / 100.0);
                return new ApiResponse<>("Payment Status", message);
            } else {
                return new ApiResponse<>("Payment Failed" + paymentStatus, "Payment did not succeed. Please try again.");
            }
        } catch (StripeException e) {
            throw new PaymentException("Error occurred while verifying payment status: " + e.getMessage());
        }
    }
}
