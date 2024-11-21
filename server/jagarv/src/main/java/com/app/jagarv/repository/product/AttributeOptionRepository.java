package com.app.jagarv.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.jagarv.entity.product.AttributeOption;

import java.util.List;

@Repository
public interface AttributeOptionRepository extends JpaRepository<AttributeOption, Long> {
    List<AttributeOption> findAllById(Iterable<Long> ids);
}