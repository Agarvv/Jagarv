package com.app.jagarv.service.wishlist;

import com.app.jagarv.outil.SecurityOutil;
import com.app.jagarv.repository.wishlist.WishlistRepository;
import com.app.jagarv.dto.wishlist.WishlistDTO;
import com.app.jagarv.entity.user.User;
import com.app.jagarv.repository.user.UserRepository;
import com.app.jagarv.exception.exceptions.users.UserNotFoundException;
import com.app.jagarv.entity.wishlist.Wishlist;
import com.app.jagarv.mapper.wishlist.WishlistMapper;
import com.app.jagarv.dto.wishlist.AddToWishlistDTO;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.exceptions.exception.products.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WishlistService 
{

    private final SecurityOutil securityOutil;
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final WishlistMapper wishlistMapper;
    private final ProductRepository productRepository;

    public WishlistService
    (
    SecurityOutil securityOutil,
    WishlistRepository wishlistRepository,
    UserRepository userRepository,
    WishlistMapper wishlistMapper,
    ProductRepository productRepository
    )
    {
        this.securityOutil = securityOutil;
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.wishlistMapper = wishlistMapper;
        this.productRepository = productRepository;
    }

    public WishlistDTO getOrCreateUserWishlist() 
    {
        Wishlist wishlist = findOrCreateWishlist();
        return wishlistMapper.toDto(wishlist);
    }

    public String addOrRemoveFromWishlist(AddToWishlistDTO wishlistDTO) 
    {
        
        Product product = productRepository.findById(wishlistDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Wishlist wishlist = findOrCreateWishlist();

        if (
        wishlist.getProducts().contains(product)
        ) 
        {
            wishlist.getProducts().remove(product);
            wishlistRepository.save(wishlist);
            return "REMOVED";
        } else 
        {
            wishlist.getProducts().add(product);
            wishlistRepository.save(wishlist);
            return "ADDED";
        }
    }

    private Wishlist findOrCreateWishlist() 
    {
        Long userId = securityOutil.getAuthenticatedUserId();

        return wishlistRepository.findByUserId(userId)
                .orElseGet(() -> 
                {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFoundException("User not found"));
                    Wishlist newWishlist = new Wishlist();
                    newWishlist.setUser(user);
                    return wishlistRepository.save(newWishlist);
                });
    }
    
    public boolean isProductInWishlist
    (Long userId, Long productId) 
    {
    return wishlistRepository.existsByUserIdAndProductId(userId, productId);
    }
    
}