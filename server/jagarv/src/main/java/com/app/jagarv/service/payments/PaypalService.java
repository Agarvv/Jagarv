package com.app.jagarv.service.payments;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService {
    private final APIContext apiContext;

    public PaypalService(APIContext apiContext) {
       this.apiContext = apiContext;
    }

    public Payment createPayment(
        Long total,
        String currency,
        String method,
        String intent,
        String successUrl,
        String redirectUrl,
        String cancelUrl
    ) throws PayPalRESTException {
        var amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.valueOf(total));

        var transaction = new Transaction(); // i think i will use 'var' in the future :P
        transaction.setAmount(amount);
        
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        var payer = new Payer();
        payer.setPaymentMethod(method); 

        var payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);  
        redirectUrls.setReturnUrl(successUrl); 
        payment.setRedirectUrls(redirectUrls);
        
        return payment.create(apiContext);
    }
}
