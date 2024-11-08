package com.app.jagarv.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.jagarv.entity.product.VariantAttributeOption;

@Repository
public interface VariantAttributeOptionRepository extends JpaRepository<Long, VariantAttributeOption> {
    
}
