package com.app.jagarv.service.admin.order;

import com.app.jagarv.dto.order.read.OrdersDTO;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.mapper.orders.OrdersMapper;
import com.app.jagarv.repository.order.OrderRepository;

import org.springframework.stereotype.Service;

import com.app.jagarv.dto.payments.ProductPaymentDTO; 


import java.util.List;
import java.util.stream.Collectors;

// Our App's Orders Service.
@Service
public class AdminOrdersService {
    // Injections
    private final OrderRepository orderRepository; 
    private final OrdersMapper ordersMapper; 
    
    public AdminOrdersService(OrderRepository orderRepository, OrdersMapper ordersMapper) {
      this.orderRepository = orderRepository;
      this.ordersMapper = ordersMapper;
    }
    
    // Returns all the App's orders to the controller
    public List<OrdersDTO> getOrders() {
        return orderRepository.findAll()           .stream()
            .map(ordersMapper::orderToDTO)
            .collect(Collectors.toList());
    }

}
