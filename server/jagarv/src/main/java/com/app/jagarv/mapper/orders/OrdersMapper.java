package com.app.jagarv.mapper.orders;

import org.mapstruct.Mapper;
import com.app.jagarv.dto.order.read.OrdersDTO;
import com.app.jagarv.entity.order.Order;
import com.app.jagarv.dto.order.read.AdminOrderDTO;
import com.app.jagarv.mapper.product.ProductMapper;
import com.app.jagarv.mapper.product.AttributeOptionMapper; 
import com.app.jagarv.mapper.user.UserMapper;
import com.app.jagarv.dto.product.read.OrderProductDTO;
import com.app.jagarv.entity.product.Product;



@Mapper(componentModel = "spring", uses = { ProductMapper.class, UserMapper.class, AttributeOptionMapper.class }) 
public interface OrdersMapper {
    // Entity to DTO 
    OrdersDTO orderToDTO(Order order);
    
    // DTO to Entity 
    Order dtoToOrder(OrdersDTO ordersDTO); 
    
    AdminOrderDTO orderToAdminOrder(Order order); 

    OrderProductDTO mapProductToOrderProductDTO(Product product); 
}
