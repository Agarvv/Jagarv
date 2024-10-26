package com.app.jagarv.service.admin.order;

import com.app.jagarv.repository.OrderRepository;
import com.app.jagarv.mapper.orders.OrdersMapper;
import com.app.jagarv.dto.order.OrdersDTO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

// Our App's Orders Service.
@Service
public class AdminOrdersService {
    // Injections
    @Autowired
    private OrderRepository orderRepository; 
    
    @Autowired 
    private OrdersMapper ordersMapper; 
    
    // Returns all the App's orders to the controller
    public List<OrdersDTO> getOrders() {
        return orderRepository.findAll()           .stream()
            .map(ordersMapper::orderToDTO)
            .collect(Collectors.toList());
    }
}
