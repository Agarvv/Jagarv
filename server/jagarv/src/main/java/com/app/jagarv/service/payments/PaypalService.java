package com.app.jagarv.service.payments;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.app.jagarv.service.cart.CartService;
import com.app.jagarv.entity.user.User;
import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.outil.PaymentOutil;
import com.app.jagarv.service.admin.order.AdminOrdersService;
import com.app.jagarv.exception.exceptions.discount.DiscountCodeNotFoundException;
import com.app.jagarv.exception.exceptions.payments.PaymentException;
import com.app.jagarv.entity.discount.DiscountCode;
import com.app.jagarv.repository.discount.DiscountCodeRepository;
import com.app.jagarv.service.user.UserService;
import com.app.jagarv.exception.exceptions.users.UserMustHaveAdressException;


@Service
public class PaypalService {
    private final APIContext apiContext;
    private final CartService cartService;
    private final AdminOrdersService adminOrdersService;
    private final DiscountCodeRepository discountCodeRepository;
    private final UserService userService; 

    public PaypalService(APIContext apiContext, CartService cartService, AdminOrdersService adminOrdersService, DiscountCodeRepository discountCodeRepository, UserService userService) {
        this.apiContext = apiContext;
        this.cartService = cartService;
        this.adminOrdersService = adminOrdersService;
        this.discountCodeRepository = discountCodeRepository;
        this.userService = userService; 
    }

    public String createPayment(String discountCode) throws PayPalRESTException {
    User user = userService.findAuthenticatedUser();
    
       
        if(user.getAdress() == "" || user.getAdress() == null) {
            throw new UserMustHaveAdressException("Please set your adress before checkout.")
        }
        
    
    BigDecimal finalPrice;
    Cart cart = cartService.getUserRawCart();
    BigDecimal totalPrice = PaymentOutil.calculateCartTotalPrice(cart);

    if (discountCode != null && !discountCode.isEmpty()) { 
        DiscountCode discount = discountCodeRepository.findByDiscountCode(discountCode)
            .orElseThrow(() -> new DiscountCodeNotFoundException("Invalid discount code"));
        finalPrice = totalPrice.subtract(totalPrice.multiply(discount.getReduction()));
    } else {
        finalPrice = totalPrice; 
    }

    if (finalPrice.compareTo(BigDecimal.ZERO) <= 0) {
        throw new PaymentException("The final amount cannot be zero or negative.");
    }

    finalPrice = finalPrice.multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.HALF_UP);
    String finalPriceStr = finalPrice.toPlainString();

    String currency = "USD";
    String method = "paypal";
    String intent = "sale";
    String successUrl = "https://jagarv-jq5o.onrender.com/api/jagarv/pay/paypal/success";
    String cancelUrl = "https://jagarv-jq5o.onrender.com/api/jagarv/pay/paypal/cancel";

    Amount amount = new Amount();
    amount.setCurrency(currency);
    amount.setTotal(finalPriceStr);

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


    public String completePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment executedPayment = payment.execute(apiContext, paymentExecution);

        if ("approved".equals(executedPayment.getState())) {
            Amount amount = executedPayment.getTransactions().get(0).getAmount();
            String totalStr = amount.getTotal();
            Long totalAmount = Long.valueOf(totalStr) / 100;
            
            adminOrdersService.placeOrder(totalAmount, paymentId, "PayPal"); 
            return "¡Thanks For Your Purchase!";
        } else {
            throw new PayPalRESTException("Payment Not Approved..");
        }
    }

}