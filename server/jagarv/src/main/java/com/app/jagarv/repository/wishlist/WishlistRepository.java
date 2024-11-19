package com.app.jagarv.repository.wishlist;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import com.app.jagarv.entity.product.Product;
import com.app.jagarv.entity.wishlist.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    
    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN TRUE ELSE FALSE END " +
       "FROM Wishlist w JOIN w.products p " +
       "WHERE w.user.id = :userId AND p.id = :productId")
    Boolean existsByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    Optional<Wishlist> findByUserId(Long userId);
}