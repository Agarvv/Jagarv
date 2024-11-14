package com.app.jagarv.controller.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.dto.product.create.CreateProductOpinionDTO;

import jakarta.validation.Valid;
import com.app.jagarv.service.products.ProductOpinionService;


// endpoint for products opinions
@RestController
@RequestMapping("/api/jagarv/products/opinions")
public class ProductOpinionController {

   private final ProductOpinionService productOpinionService;
   public ProductOpinionController(ProductOpinionService productOpinionService) {
    this.productOpinionService = productOpinionService;
   }
   
   // creates a product opinion
   @PostMapping("/create")
   public ResponseEntity<ApiResponse<Void>> createProductOpinion(
    @Valid @RequestBody CreateProductOpinionDTO opinion
    ) {
       productOpinionService.createProductOpinion(opinion);
       return ResponseEntity.ok(new ApiResponse<>("Opinion Created", null));
    }
}