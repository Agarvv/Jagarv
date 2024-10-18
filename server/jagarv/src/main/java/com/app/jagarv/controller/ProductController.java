package com.app.jagarv.controller;

import com.app.jagarv.dto.ProductDTO;
import com.app.jagarv.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jagarv/products")
public class ProductController {
    
    @Autowired 
    private ProductService productService;  
    
        @GetMapping
    public ResponseEntity<Object> getProducts() {
        List<ProductDTO> products = productService.getProducts();
        if (products.isEmpty()) {
            // im doing this condition just to check if the DB and the Spring server is working together correctly.
            Map<String, String> response = new HashMap<>();
            response.put("message", "No products available");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.ok(products);
        }
    }


}