package com.app.jagarv.outil;

import com.app.jagarv.entity.product.Product;

import java.util.List;

public class PaymentOutil {
    
    // calculates the price of products map
    public static long calculateProductsPrice(List<Product> products) {
        return products.stream()
                       .mapToLong(product -> product.getPrice().longValueExact())
                       .sum();
    }
    

}