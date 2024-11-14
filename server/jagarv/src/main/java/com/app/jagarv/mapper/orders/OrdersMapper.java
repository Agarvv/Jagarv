package com.app.jagarv.mapper.orders;

import org.mapstruct.Mapper;

import com.app.jagarv.dto.order.read.OrdersDTO;
import com.app.jagarv.entity.order.Order;

@Mapper(componentModel = "spring") 
public interface OrdersMapper {
    // Entity to DTO 
    OrdersDTO orderToDTO(Order order);
    
    // DTO to Entity 
    Order dtoToOrder(OrdersDTO ordersDTO); 
}