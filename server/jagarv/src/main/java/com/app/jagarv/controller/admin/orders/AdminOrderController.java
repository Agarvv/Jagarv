package com.app.jagarv.controller.admin.orders;

import com.app.jagarv.dto.order.read.OrdersDTO;
import com.app.jagarv.service.admin.order.AdminOrdersService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.app.jagarv.dto.ApiResponse; 
import com.app.jagarv.dto.order.edit.SetOrderDTO;


// THE APP'S ORDERS CONTROLLERS
@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {
    
    private final AdminOrdersService ordersService; 

    public AdminOrderController(AdminOrdersService ordersService) {
        this.ordersService = ordersService;
    }
    
    // Returns all the orders of the app
    @GetMapping 
    public List<OrdersDTO> getOrders() {
        return ordersService.getOrders();
    }
    
    // sets order status as being processed
    @PostMapping("/setInProcess") 
    public ResponseEntity<ApiResponse<Void>> 
    setOrderInProcess(@Valid @RequestBody SetOrderDTO order) {
       ordersService.setOrderInProcces(order);

        return ResponseEntity.ok
        (new ApiResponse<>("Order status setted as In Process", null));
    }
    
    // sets order status as arrived
    @PostMapping("/setArrived")
    public ResponseEntity<ApiResponse<Void>>
    setOrderArrived(@Valid @RequestBody SetOrderDTO order) {
        ordersService.setOrderArrived(order);
        return ResponseEntity.ok(new ApiResponse<>("Order status setted as arrived", null));
    }

    
}
