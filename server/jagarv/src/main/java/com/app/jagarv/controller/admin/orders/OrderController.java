package com.app.jagarv.controller.admin.orders;

import com.app.jagarv.dto.order.OrdersDTO; 
import com.app.jagarv.service.admin.order.OrdersService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


// THE APP'S ORDERS CONTROLLERS
@RestController
@RequestMapping("/admin/orders")
public class OrderController {
    
    // Injections
    @Autowired
    private OrdersService ordersService; 
    
    // Returns all the orders of the app
    @GetMapping 
    public List<OrdersDTO> getOrders() {
        return ordersService.getOrders();
    }
    
}
