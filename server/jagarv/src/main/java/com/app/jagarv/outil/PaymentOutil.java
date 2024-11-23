package com.app.jagarv.outil;

import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.entity.cart.CartItem;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.exception.exceptions.products.RunOfStockException;

import java.math.BigDecimal;
import java.util.List;

public class PaymentOutil {
    
    // calculates the price of products map
     public static BigDecimal calculateCartTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItem item : cart.getCartItems()) {
            Product product = item.getProduct();
    
            if (product.getStock() == 0) {
                throw new RunOfStockException("Please check the stock of our products before buying.");
            }
    
            totalPrice = totalPrice.add(product.getPrice());
        }
    
        if (totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Cart total must be greater than 0.");
        }
    
        return totalPrice;
    }
}