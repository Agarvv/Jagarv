package com.app.jagarv.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jagarv.entity.order.Order;



public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
