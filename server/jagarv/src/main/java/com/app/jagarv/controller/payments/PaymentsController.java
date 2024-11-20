package com.app.jagarv.controller.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.service.payments.PaypalService;
import com.app.jagarv.service.payments.StripeService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;

import jakarta.servlet.http.HttpServletRequest;

import com.app.jagarv.dto.payments.ProductPaymentDTO;

@RestController
@RequestMapping("/api/jagarv/pay")
public class PaymentsController {
}
