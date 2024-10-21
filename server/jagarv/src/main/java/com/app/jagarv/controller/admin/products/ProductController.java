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
    @PostMapping(value = "/create", consumes = { "multipart/form-data" })
    public ResponseEntity<String> createProduct(
    @RequestPart("product") CreateProductDTO createProductDTO, 
    @RequestPart("pictures") MultipartFile[] pictures) {

    productService.createProduct(createProductDTO, pictures);
    return ResponseEntity.ok("Product created successfully.");
}
  
}