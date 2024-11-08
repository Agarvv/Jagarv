package com.app.jagarv.controller.products;

import com.app.jagarv.dto.product.CreateAttributeOptionDTO;
import com.app.jagarv.service.products.AttributeOptionService;
import com.app.jagarv.dto.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jagarv/admin/products/attributes/options")
public class AttributeOptionController {

    private final AttributeOptionService optionService;

    @Autowired
    public AttributeOptionController(AttributeOptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Void>> createOption(@RequestBody CreateAttributeOptionDTO optionDTO) {
        optionService.createOption(optionDTO);
        return ResponseEntity.ok(new ApiResponse<>("Attribute Option Created", null));
    }

}