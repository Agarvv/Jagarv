package com.app.jagarv.controller.products;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.dto.product.CreateCategoryDTO;
import com.app.jagarv.service.products.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jagarv/admin/products/category")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // creates a new category
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Void>> createCategory(@Valid @RequestBody CreateCategoryDTO category) {
        categoryService.createCategory(category);
        return ResponseEntity.ok(new ApiResponse<>("Category Created", null)); 
    }
}