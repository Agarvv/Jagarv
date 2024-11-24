package com.app.jagarv.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jagarv.entity.order.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Boolean existsByUserIdAndProducts_Id(Long userId, Long productId);
    List<Order> findAllByUserId(Long userId);
}