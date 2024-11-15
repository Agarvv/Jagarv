package com.app.jagarv.outil;

import com.app.jagarv.entity.product.Product;

import java.util.Map;

public class PaymentOutil {
    
    // calculates the price of products map
    public static Long calculateProductsPrice(Map<Long, Product> products) {
        return products.values().stream()
                .mapToLong(Product::getPrice)
                .sum();
    }
    
}