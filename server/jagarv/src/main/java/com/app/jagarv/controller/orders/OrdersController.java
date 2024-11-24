package com.app.jagarv.controller.orders; 

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.app.jagarv.dto.order.read.OrdersDTO; 
import com.app.jagarv.service.orders.OrdersService;


@RestController 
@RequestMapping("/api/jagarv/orders")
public class OrdersController {
    private final OrdersService ordersService; 
    
    public OrdersController
    (
      OrdersService ordersService 
    )
    {
        this.ordersService = ordersService; 
    }
    
    @GetMapping 
    public ResponseEntity<List<OrdersDTO>>
    getUserOrders() 
    {
        List<OrdersDTO> orders = ordersService.getUserOrders();
        return ResponseEntity.ok(orders);
    }
}