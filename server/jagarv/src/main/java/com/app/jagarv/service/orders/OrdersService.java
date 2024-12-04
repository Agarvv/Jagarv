package com.app.jagarv.service.orders;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors; 

import com.app.jagarv.dto.order.read.OrdersDTO;
import com.app.jagarv.outil.SecurityOutil;
import com.app.jagarv.repository.order.OrderRepository;
import com.app.jagarv.mapper.orders.OrdersMapper;

@Service
public class OrdersService {
    private final SecurityOutil securityOutil;
    private final OrderRepository orderRepository;
    private final OrdersMapper ordersMapper;

    public OrdersService(
            SecurityOutil securityOutil,
            OrderRepository orderRepository,
            OrdersMapper ordersMapper
    ) {
        this.securityOutil = securityOutil;
        this.orderRepository = orderRepository;
        this.ordersMapper = ordersMapper;
    }

    public List<OrdersDTO> getUserOrders() {
        Long userId = securityOutil.getAuthenticatedUserId();
        return orderRepository.findOrdersByUserId(userId)
                .stream()
                .map(ordersMapper::orderToDTO)
                .collect(Collectors.toList());
    }
}