package com.app.jagarv.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jagarv.entity.cart.Cart;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // Trouver un Cart par userId
    Optional<Cart> findByUserId(Long userId);
}
