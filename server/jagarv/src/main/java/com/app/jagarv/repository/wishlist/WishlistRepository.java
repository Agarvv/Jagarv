package com.app.jagarv.repository.wishlist;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jagarv.entity.product.Product;
import com.app.jagarv.entity.wishlist.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Boolean existsByUserIdAndProductId(Long userId, Long productId);

    Optional<Wishlist> findByUserId(Long userId);
}