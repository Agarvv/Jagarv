package com.app.jagarv.repository.discount; 

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jagarv.entity.discount.DiscountCode; 

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    
}