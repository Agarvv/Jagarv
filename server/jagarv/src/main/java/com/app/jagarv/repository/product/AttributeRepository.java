package com.app.jagarv.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.jagarv.entity.product.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Long, Attribute> {
    
}
