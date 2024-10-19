package com.app.jagarv.repository;

import com.app.jagarv.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository 
public interface SalesRepository extends JpaRepository<Sale, Long> {
    
}
