package com.app.jagarv.controller.cart; 

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.service.cart.CartService;
import com.app.jagarv.dto.cart.create.AddToCartDTO;
import com.app.jagarv.dto.cart.read.CartDTO;

// The App Cart Controller, Handles all the user's carts logic.
@RestController 
@RequestMapping("/api/jagarv/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    // gets the user cart 
    @GetMapping 
    public ResponseEntity<CartDTO> getUserCart()
    {
        return ResponseEntity.ok(cartService.getUserCart());
    }

    // this endpoint finds a item on the user cart, and if that item already exists, removes it
    @PostMapping("/addOrRemove")
    public ResponseEntity<ApiResponse<Void>> addToCart(@Valid @RequestBody AddToCartDTO addToCartDTO) {
      String message = cartService.addOrRemoveToCart(addToCartDTO); 
      // message can be either "Added" or "Removed" depending.
      return ResponseEntity.ok(new ApiResponse<>(message, null));
    }
}