package com.app.jagarv.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jagarv.entity.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndProductId(Long cartId, Long productId);
}
