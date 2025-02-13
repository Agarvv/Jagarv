package com.app.jagarv.controller.admin.products;

import com.app.jagarv.service.products.AttributeOptionService;
import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.dto.product.create.CreateAttributeOptionDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products/attributes/options")
public class AttributeOptionController {

    private final AttributeOptionService optionService;

    public AttributeOptionController(AttributeOptionService optionService) {
        this.optionService = optionService;
    }
    
    // creates attribute option for a product
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Void>> createOption(@RequestBody CreateAttributeOptionDTO optionDTO) {
        optionService.createOption(optionDTO);
        return ResponseEntity.ok(new ApiResponse<>("Attribute Option Created", null));
    }

}