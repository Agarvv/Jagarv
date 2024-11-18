package com.app.jagarv.controller.wishlist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jagarv.dto.wishlist.WishlistDTO;
import com.app.jagarv.dto.wishlist.AddToWishlistDTO;
import com.app.jagarv.dto.ApiResponse; 

import com.app.jagarv.service.wishlist.WishlistService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/jagarv/wishlist")
public class WishlistController 
{

    private final WishlistService wishlistService;

    public WishlistController
    (
        WishlistService wishlistService
    ) 
    
    {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<WishlistDTO> getUserWishlist() 
    {
        WishlistDTO wishlist = wishlistService.getOrCreateUserWishlist();
        return ResponseEntity.ok(wishlist);
    }
    
    @PostMapping("/add") 
    public ResponseEntity<ApiResponse<Void>>
    addOrRemoveFromWishlist
    (@Valid AddToWishlistDTO wishlist) 
    {
        String message = wishlistService.addOrRemoveFromWishlist(wishlist); 
        
        return ResponseEntity.ok(
          new ApiResponse<>(message, null)
        );
    }
}