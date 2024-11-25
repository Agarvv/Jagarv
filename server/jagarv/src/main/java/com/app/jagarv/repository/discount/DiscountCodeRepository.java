package com.app.jagarv.repository.discount; 

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jagarv.entity.discount.DiscountCode;
import java.util.List;
import java.util.Optional;


public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    Optional<DiscountCode> findByDiscountCode(String discountCode);
}