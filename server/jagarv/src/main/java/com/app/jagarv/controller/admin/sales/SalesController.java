package com.app.jagarv.controller.admin.sales;

import com.app.jagarv.service.admin.sales.SalesService;
import com.app.jagarv.dto.sales.SalesDTO;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// The App's Sales Controller
@RestController 
@RequestMapping("/admin/sales")
public class SalesController {
    // Injections
    @Autowired 
    private SalesService salesService; 
    
    // Returns all the app's sales
    @GetMapping
    public List<SalesDTO> getSales() {
        return salesService.getSales();
    }

}
