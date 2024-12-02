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
import com.stripe.model.PaymentIntent;
import com.app.jagarv.outil.SendMail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@Service
public class StripeService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final AdminOrdersService adminOrdersService;
    private final CartService cartService;
    private final SendMail sendMail; 

    @Value("${stripe.secret}")
    private String stripeApiKey;

    @Value("${stripe.webhook_secret}")
    private String webhookSecret;

    public StripeService(
        ProductRepository productRepository,
        CartRepository cartRepository,
        AdminOrdersService adminOrdersService,
        CartService cartService,
        SendMail sendMail 
    ) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.adminOrdersService = adminOrdersService;
        this.cartService = cartService;
        this.sendMail = sendMail; 
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
        .setQuantity(1L)  // just for now
        .build();


    SessionCreateParams params = SessionCreateParams.builder()
        .addLineItem(lineItem)  
        .setMode(SessionCreateParams.Mode.PAYMENT)
        .setSuccessUrl("https://jagarv-jq5o.onrender.com/api/jagarv/pay/stripe/success?session_id={CHECKOUT_SESSION_ID}")
        .setCancelUrl("https://jagarv.vercel.app/cancelPayment")
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
            case "payment_intent.succeeded":
                handlePaymentIntentSucceeded(event);
                sendMail.sendMail("casluagarv@gmail.com", 
                    "Stripe Webhook Success", 
                    "Payment succeeded and processed successfully.");
                break;
            default:
                sendMail.sendMail("casluagarv@gmail.com", 
                    "Stripe Webhook Unhandled Event", 
                    "Unhandled event type: " + event.getType());
                break;
        }
    } catch (Exception ex) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        String exceptionAsString = baos.toString();

        sendMail.sendMail(
            "casluagarv@gmail.com",
            "Stripe Webhook Exception Occurred",
            "An error occurred during Stripe Webhook processing:\n\n" + exceptionAsString
        );


        throw new PaymentException("Something went wrong with your payment: " + ex.getMessage(), ex);
    }
}



private void handlePaymentIntentSucceeded(Event event) {
    try {
        Object obj = event.getData().getObject();
        if (!(obj instanceof PaymentIntent)) {
            throw new PaymentException("Invalid event object: not a PaymentIntent");
        }
        PaymentIntent paymentIntent = (PaymentIntent) obj;
         String paymentIntentId = paymentIntent.getId();
        if (paymentIntentId == null) {
            throw new PaymentException("PaymentIntent ID is null");
        }
        Long amountReceived = paymentIntent.getAmountReceived();
        if (amountReceived == null || amountReceived <= 0) {
            throw new PaymentException("Invalid amount received: " + amountReceived);
        }
        adminOrdersService.placeOrder(amountReceived, paymentIntentId, "Stripe");
    } catch (Exception ex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        String exceptionAsString = baos.toString();


        sendMail.sendMail(
            "casluagarv@gmail.com",
            "Payment Intent Handling Exception",
            "An error occurred while processing the payment intent:\n\n" + exceptionAsString
        );

        throw new PaymentException("Something went wrong with your payment (2): " + ex.getMessage(), ex);
    }
}


}
 