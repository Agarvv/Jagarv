package com.app.jagarv.controller.admin.products;

import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.service.admin.product.ProductService;
import com.app.jagarv.dto.product.CreateProductDTO;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/admin/products")
public class ProductController {
    
    @Autowired 
    private ProductService productService;  
    
    @GetMapping
    public List<ProductDTO> getProducts() {
       return productService.getProducts();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {
       productService.createProduct(createProductDTO);
       return ResponseEntity.ok("Product created successfully.");
    }
   
    




}