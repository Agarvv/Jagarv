package com.app.jagarv.controller.admin.orders;

import com.app.jagarv.dto.order.read.OrdersDTO;
import com.app.jagarv.service.admin.order.AdminOrdersService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


// THE APP'S ORDERS CONTROLLERS
@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {
    
    // Injections
    @Autowired
    private AdminOrdersService ordersService; 
    
    // Returns all the orders of the app
    @GetMapping 
    public List<OrdersDTO> getOrders() {
        return ordersService.getOrders();
    }
    
}
