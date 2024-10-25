package com.app.jagarv.controller.admin.products;


import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.service.admin.product.ProductService;
import com.app.jagarv.dto.product.CreateProductDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// ADMIN PRODUCTS CONTROLLER 
@RestController
@RequestMapping("/admin/products")
public class ProductController {
    
    // INJECTIONS
    @Autowired 
    private ProductService productService;  
    
    // GETS ALL THE PRODUCTS FROM THE DB
    @GetMapping
    public List<ProductDTO> getProducts() {
       return productService.getProducts();
    }
    
    // CREATES A NEW PRODUCT USING FORM DATA FOR THE FILES
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createProduct(@RequestBody CreateProductDTO createProductDTO) {
    productService.createProduct(createProductDTO);
    
    // Java, java java... 
    Map<String, String> response = new HashMap<>();
    response.put("message", "Product created successfully.");
    
    return ResponseEntity.ok(response);
}

    // Delete a product by product id
    @DeleteMapping("/delete/{productId}") 
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long productId) {

        productService.deleteProduct(productId);

        Map<String, String> response = new HashMap<>();

        response.put("message", "Product deleted successfully");

        return ResponseEntity.ok(response);

    }
 
}