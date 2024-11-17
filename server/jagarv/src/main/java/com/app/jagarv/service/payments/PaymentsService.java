package com.app.jagarv.service.payments;

// import java.util.Map;

// import org.springframework.stereotype.Service;

// import com.app.jagarv.dto.payments.ProductPaymentDTO; 
// import com.app.jagarv.repository.product.ProductRepository;
// import com.app.jagarv.entity.product.Product;  
// import com.app.jagarv.outil.ProductOutil; 
// import com.app.jagarv.outil.PaymentOutil; 
// import com.app.jagarv.service.payments.PaypalService;
// import com.paypal.api.payments.Payment;

// import com.paypal.base.rest.PayPalRESTException;

// @Service
// public class PaymentsService {
//     // private final ProductRepository productRepository;
//     // private final ProductOutil productOutil; 
//     // private final PaymentOutil paymentOutil;
//     // private final PaypalService paypalService;

//     // public PaymentsService(PaypalService paypalService, ProductRepository productRepository, ProductOutil productOutil, PaymentOutil paymentOutil) {
//     //     this.productRepository = productRepository;
//     //     this.productOutil = productOutil;
//     //     this.paymentOutil = paymentOutil;
//     //     this.paypalService = paypalService;
//     // }

//     // public String handlePayment(ProductPaymentDTO productPaymentDTO) {
//     //     Map<Long, Product> productsMap = productOutil.findAllById(productPaymentDTO.getProductIds());
//     //     Long finalPrice = paymentOutil.calculateProductsPrice(productsMap); 

//     //     try {
//     //         Payment payment = paypalService.createPayment(
//     //             finalPrice, "USD", "paypal", "sale",
//     //             "https://jagarv.vercel.app", 
//     //             "https://jagarv.vercel.app/register",
//     //             "https://jagarv.vercel.app/login"
//     //         );

//     //         for(var link : payment.getLinks()) {
//     //             if(link.getRel().equalsIgnoreCase("approval_url")) {
//     //                 return link.getHref(); 
//     //             }
//     //         }
//     //         return "No approval URL found";

//     //     } catch (PayPalRESTException e) {
//     //         throw new RuntimeException("paypal error.."); // i will create a exception for this later
//     //     }
//     // }
// }