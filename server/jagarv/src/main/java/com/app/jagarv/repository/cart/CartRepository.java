package com.app.jagarv.repository.cart; 

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jagarv.entity.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
    
}