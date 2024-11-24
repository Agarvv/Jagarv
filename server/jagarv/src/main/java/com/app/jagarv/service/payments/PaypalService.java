package com.app.jagarv.service.payments;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import com.app.jagarv.service.cart.CartService;
import com.app.jagarv.entity.cart.Cart; 
import com.app.jagarv.outil.PaymentOutil;
import com.app.jagarv.service.admin.order.AdminOrdersService; 

@Service
public class PaypalService {
    private final APIContext apiContext;
    private final CartService cartService;
    private final AdminOrdersService adminOrdersService; 

    public PaypalService(APIContext apiContext, CartService cartService, AdminOrdersService adminOrdersService) {
        this.apiContext = apiContext;
        this.cartService = cartService;
        this.adminOrdersService = adminOrdersService; 
    }

    public String createPayment() throws PayPalRESTException {
        Cart cart = cartService.getUserRawCart();
        BigDecimal finalPrice = PaymentOutil.calculateCartTotalPrice(cart);

        String currency = "USD";
        String method = "paypal";
        String intent = "sale";
        String successUrl = "http://localhost:8080/api/jagarv/pay/paypal/success";
        String cancelUrl = "http://localhost:8080/api/jagarv/pay/paypal/cancel";

        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.valueOf(finalPrice));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(apiContext);

        return createdPayment.getLinks().stream()
            .filter(link -> "approval_url".equals(link.getRel()))
            .findFirst()
            .map(link -> link.getHref())
            .orElseThrow(() -> new PayPalRESTException("Something Went Wrong..."));
    }

    public String completePayment
    (String paymentId, String payerId) 
    throws PayPalRESTException 
    {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment executedPayment = payment.execute(apiContext, paymentExecution);

        if ("approved".equals(executedPayment.getState())) {
            
            
            return "¡Thanks For Your Purchase!";
        } else {
            throw new PayPalRESTException("Payment Not Aproved..");
        }
        
    }
}