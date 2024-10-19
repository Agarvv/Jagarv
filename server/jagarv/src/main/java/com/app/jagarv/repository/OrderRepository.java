package com.app.jagarv.repository;

import com.app.jagarv.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
