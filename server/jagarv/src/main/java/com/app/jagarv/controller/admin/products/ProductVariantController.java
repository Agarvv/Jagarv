package com.app.jagarv.controller.admin.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.dto.product.create.CreateProductVariantDTO;
import com.app.jagarv.service.products.ProductVariantService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/admin/products/variants")
public class ProductVariantController {
    
    private final ProductVariantService productVariantService; 
    
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }
    
    @PostMapping("/create") 
    public ResponseEntity<ApiResponse<Void>> 
    createProductVariant(@Valid @RequestBody CreateProductVariantDTO variant) {
        productVariantService.createProductVariant(variant);
        return ResponseEntity.ok(new ApiResponse<>("Variant Created Succesfully", null));
    }
    
    
}