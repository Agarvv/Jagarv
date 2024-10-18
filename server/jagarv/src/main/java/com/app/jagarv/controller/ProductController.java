package com.app.jagarv.controller;

import com.app.jagarv.dto.ProductDTO;
import com.app.jagarv.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/jagarv/products")
public class ProductController {
    
    @Autowired 
    private ProductService productService;  
    
    @GetMapping
    public List<ProductDTO> getProducts() {
       return productService.getProducts();
    }


}