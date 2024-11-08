package com.app.jagarv.controller.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.dto.product.CreateAttributeDTO;
import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.service.products.AttributeService;

import jakarta.validation.Valid; 



// handles all product's attribute logic.
// this is important for good product variants system
@RestController 
@RequestMapping("/api/jagarv/admin/products/attributes")
public class AttributeController {
    
    private final AttributeService attributeService;
    
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }
    
    // creates a new attribute, ("color", "size", "ram")...
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Void>> createAttribute(@Valid @RequestBody CreateAttributeDTO attribute) {
        
        attributeService.createAttribute(attribute);
        
        return ResponseEntity.ok(new ApiResponse<>("Attribute Created", null));
    }
}