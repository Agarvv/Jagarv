package com.app.jagarv.controller.cart; 

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.dto.cart.AddToCartDTO;
import com.app.jagarv.service.cart.CartService;

import com.app.jagarv.dto.cart.CartDTO;

// The App Cart Controller, Handles all the user's carts logic.
@RestController 
@RequestMapping("/api/jagarv/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    @GetMapping 
    public ResponseEntity<CartDTO> getUserCart()
    {
        return ResponseEntity.ok(cartService.getUserCart());
    }

    // this endpoint finds a item on the user cart, and if that item already exists, removes it
    @PostMapping("/addOrRemove")
    public ResponseEntity<ApiResponse<Void>> addToCart(@Valid @RequestBody AddToCartDTO addToCartDTO) {
      cartService.addOrRemoveToCart(addToCartDTO);
      return ResponseEntity.ok(new ApiResponse<>("Product added to cart", null));
    }
}