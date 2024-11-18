package com.app.jagarv.repository.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jagarv.entity.wishlist.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Boolean existsByUserIdAndProductId(Long userId, Long productId);
}