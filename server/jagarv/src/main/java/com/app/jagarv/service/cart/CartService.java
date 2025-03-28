package com.app.jagarv.service.cart;

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
import com.app.jagarv.dto.cart.create.AddToCartDTO;
import com.app.jagarv.dto.cart.read.CartDTO;
import com.app.jagarv.mapper.cart.CartMapper; 
import com.app.jagarv.exception.exceptions.products.RunOfStockException;
import com.app.jagarv.repository.product.AttributeOptionRepository; 
import com.app.jagarv.entity.product.AttributeOption;
import java.util.List; 

// the app user's cart service
@Service 
public class CartService {
    private final ProductRepository productRepository; 
    private final CartRepository cartRepository; 
    private final CartItemRepository cartItemRepository; 
    private final SecurityOutil securityOutil;
    private final CartMapper cartMapper; 
    private final AttributeOptionRepository attributeOptionRepository; 

    public CartService(
        ProductRepository productRepository,
        CartRepository cartRepository,
        CartItemRepository cartItemRepository,
        SecurityOutil securityOutil,
        CartMapper cartMapper,
        AttributeOptionRepository attributeOptionRepository
    ) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.securityOutil = securityOutil;
        this.cartMapper = cartMapper; 
        this.attributeOptionRepository = attributeOptionRepository;
    }
    
    // get the user cart
    public CartDTO getUserCart() {
        Long userId = securityOutil.getAuthenticatedUserId();
        
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> 
         new CartNotFoundException("Something is wrong with your cart, Please try again later")
       ); // if user cart does not exist, throw exception
       
       return cartMapper.toDto(cart);
    }
    
    // adds or remove a product from the user card, if product exists, delete it
    public String addOrRemoveToCart(AddToCartDTO addToCartDTO) {

        Long userId = securityOutil.getAuthenticatedUserId(); // authenticated user id

        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> 
         new CartNotFoundException("Something is wrong with your cart, Please try again later...")
       ); // if user cart does not exist, throw exception

        Product product = productRepository.findById(addToCartDTO.getProductId())
            .orElseThrow(() 
            -> new ProductNotFoundException
            ("The product that you want to add to your cart does not exist.")); 
            // if product not exist throw exception
            
        if(product.getStock() < addToCartDTO.getQuantity()) 
        {
            throw new RunOfStockException("That Product is run of stock"); 
        }

            CartItem existingCartItem = cartItemRepository.findByCartIdAndProductId(
                cart.getId(), product.getId()
            );
            
            if(existingCartItem != null) {
                // if the item already exists on that cart, delete it.
                cartItemRepository.delete(existingCartItem);
                return "REMOVED"; // will be returned as response in the controller
            }
            
            // find the product options that user have choosed 
            List<AttributeOption> options = attributeOptionRepository.findAllById(addToCartDTO.getOptions()); 

            // if not, creates a new item to that cart 
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setOptions(options);
            newItem.setQuantity(addToCartDTO.getQuantity()); 
            cartItemRepository.save(newItem);

            return "ADDED"; // will be returned as response in the controller
    }
    
    public Boolean inCart(Long userId, Long productId) 
    {
        return cartRepository.existsByUserIdAndCartItemsProductId(userId, productId); 
    }
    
    // will be used on payment services when we need the raw user cart
    public Cart getUserRawCart() {
        Long userId = securityOutil.getAuthenticatedUserId();
        if(userId == null) {
            throw new NullPointerException("Tried to find user cart but user cart is null..."); // debug
        }
        
        Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new CartNotFoundException("Something went wrong with your cart..."));

        for(CartItem item: cart.getCartItems()) {
            if(item.getProduct().getStock() == 0) {
                throw new RunOfStockException("Please verify the stock of the products before buying.");
            }
        }

        return cart;
    }
}