package com.app.jagarv.controller.products;

import com.app.jagarv.service.products.ProductsService;
import com.app.jagarv.dto.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jagarv/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productsService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }
}
