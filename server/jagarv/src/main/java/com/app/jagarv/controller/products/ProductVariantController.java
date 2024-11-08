package com.app.jagarv.controller.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.service.products.ProductVariantService;
import com.app.jagarv.dto.product.CreateProductVariantDTO;


@RestController 
@RequestMapping("/api/jagarv/admin/products/variants")
public class ProductVariantController {
    
    private final ProductVariantService productVariantService; 
    
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }
    
    @PostMapping("/create") 
    public ResponseEntity<ApiResponse<Void>> 
    createProductVariant(CreateProductVariantDTO variant) {
        productVariantService.createProductVariant(variant);
        return ResponseEntity.ok(new ApiResponse<>("Variant Created Succesfully", null));
    }
    
    
}