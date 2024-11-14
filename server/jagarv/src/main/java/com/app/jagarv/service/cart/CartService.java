package com.app.jagarv.service.cart;

import com.app.jagarv.dto.cart.AddToCartDTO;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.exception.exceptions.cart.CartNotFoundException;
import com.app.jagarv.exception.exceptions.products.ProductNotFoundException;
import com.app.jagarv.repository.product.ProductRepository;
import org.springframework.stereotype.Service;
import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.entity.cart.CartItem; 
import com.app.jagarv.repository.cart.CartRepository;
import com.app.jagarv.repository.cart.CartItemRepository;
import com.app.jagarv.outil.SecurityOutil;
import com.app.jagarv.dto.cart.CartDTO;

import com.app.jagarv.mapper.CartMapper; 

// the app user's cart service
@Service 
public class CartService {
    private final ProductRepository productRepository; 
    private final CartRepository cartRepository; 
    private final CartItemRepository cartItemRepository; 
    private final SecurityOutil securityOutil;
    private final CartMapper cartMapper; 

    public CartService(
        ProductRepository productRepository,
        CartRepository cartRepository,
        CartItemRepository cartItemRepository,
        SecurityOutil securityOutil,
        CartMapper cartMapper
    ) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.securityOutil = securityOutil;
        this.cartMapper = cartMapper; 
    }
    
    public CardtDTO getUserCart() {
        Long userId = securityOutil.getAuthenticatedUserId();
        
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> 
         new CartNotFoundException("Something is wrong with your cart, Please try again later")
       ); // if user cart does not exist, throw exception
       
       return cartMapper.toDto(cart);
    }
    
    public void addOrRemoveToCart(AddToCartDTO addToCartDTO) {

        Long userId = securityOutil.getAuthenticatedUserId(); // authenticated user id

        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> 
         new CartNotFoundException("Something is wrong with your cart, Please try again later...")
       ); // if user cart does not exist, throw exception

        Product product = productRepository.findById(addToCartDTO.getProductId())
            .orElseThrow(() 
            -> new ProductNotFoundException
            ("The product that you want to add to your cart does not exist.")); 
            // if product not exist throw exception 

            CartItem existingCartItem = cartItemRepository.findByCartIdAndProductId(
                cart.getId(), product.getId()
            );
            
            if(existingCartItem != null) {
                // if the item already exists on that cart, delete it.
                cartItemRepository.delete(existingCartItem);
            }

            // if not, creates a new item to that cart 
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setOptions(addToCartDTO.getOptions());
            cartItemRepository.save(newItem);
    }
}