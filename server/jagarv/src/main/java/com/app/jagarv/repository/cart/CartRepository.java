package com.app.jagarv.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jagarv.entity.cart.Cart;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Boolean existsByUserIdAndCartItemsProductId(Long userId, Long productId);
    
    Cart findByUserId(Long userId); 
}

