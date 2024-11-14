package com.app.jagarv.controller.admin.products;

import com.app.jagarv.dto.product.create.CreateProductDTO;
import com.app.jagarv.dto.product.read.ProductDTO;
import com.app.jagarv.dto.product.create.FeatureProductDTO;
import com.app.jagarv.service.admin.product.AdminProductService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.app.jagarv.dto.ApiResponse;


// ADMIN PRODUCTS CONTROLLER 
@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    // INJECTIONS
    private final AdminProductService productService;

    public AdminProductController(AdminProductService productService) {
       this.productService = productService;
    }

    // GETS ALL THE PRODUCTS FROM THE DB
    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    // CREATE A NEW PRODUCT
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Long>> createProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {
        ProductDTO createdProduct = productService.createProduct(createProductDTO); // Get the created product DTO
        
        return ResponseEntity.ok(new ApiResponse<>("Product created!", createdProduct.getId()));
    }

    // DELETE A PRODUCT BY PRODUCT ID
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse<Long>> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        
        return ResponseEntity.ok(new ApiResponse<Long>("Product deleted with id", productId));
    }

    // FEATURE A PRODUCT
    @PostMapping("/feature")
    public ResponseEntity<ApiResponse<Long>>
    featureProduct(@RequestBody @Valid FeatureProductDTO feature) {
    Long productId = feature.getProductId();

    // message can be "Product featured" or "Product unfeatured", i use this for informing the frontend and making changes in the UI.
    String message = productService.featureProduct(productId);
    
    return ResponseEntity.ok(new ApiResponse<Long>(message, productId));
    
    }
    
    // update a prouct
    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse<Long>> updateProduct(
        @RequestBody CreateProductDTO updateProductDTO,
        @PathVariable Long productId
    ) {
      // yes, im using the same dto that we use in the create endpoint. i need the same validations, that's why
      productService.updateProduct(productId, updateProductDTO);
      return ResponseEntity.ok(new ApiResponse<Long>("Product Updated!", productId));
    }
    
}