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

    // CREATE A NEW PRODUCT
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        ProductDTO createdProduct = productService.createProduct(createProductDTO); // Get the created product DTO
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product created successfully with ID: " + createdProduct.getId());
        return ResponseEntity.ok(response);
    }

    // DELETE A PRODUCT BY PRODUCT ID
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product deleted successfully");
        return ResponseEntity.ok(response);
    }

    // FEATURE A PRODUCT
    @PostMapping("/feature")
    public ResponseEntity<Map<String, String>>
    featureProduct(@RequestBody Map<String, Long> request) {
        
    Long productId = request.get("productId");
    
    // message can be "Product featured" or "Product unfeatured", i use this for informing the frontend and making changes in the UI.
    String message = productService.featureProduct(productId);
    
    Map<String, String> response = new HashMap<>();
    
    response.put("message", message);
    
    return ResponseEntity.ok(response);
    
    }
    
    
}