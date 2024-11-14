package com.app.jagarv.repository.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.jagarv.entity.sale.Sale;


@Repository 
public interface SalesRepository extends JpaRepository<Sale, Long> {
    
}
