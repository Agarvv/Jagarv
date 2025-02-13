package com.app.jagarv.service.products;

import com.app.jagarv.dto.product.read.ProductDTO;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.mapper.product.ProductMapper;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.exception.exceptions.products.ProductNotFoundException;

import org.springframework.stereotype.Service;

import com.app.jagarv.dto.product.read.ProductSummaryDTO;
import com.app.jagarv.mapper.product.ProductSummaryMapper;
import com.app.jagarv.repository.product.ProductCategoryRepository;
import com.app.jagarv.exception.exceptions.products.CategoryNotFoundException; 

import com.app.jagarv.service.wishlist.WishlistService; 
import com.app.jagarv.outil.SecurityOutil; 
import com.app.jagarv.repository.cart.CartRepository; 
import com.app.jagarv.repository.order.OrderRepository;
import com.app.jagarv.dto.product.read.BestSellerDTO;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import java.math.BigDecimal;

@Service
public class ProductsService {

    private final ProductRepository productRepository; 
    private final ProductMapper productMapper;
    private final ProductCategoryRepository productCategoryRepository; 
    private final ProductSummaryMapper productSummaryMapper; 
    private final WishlistService wishlistService;
    private final SecurityOutil securityOutil;
    private final CartRepository cartRepository; 
    private final OrderRepository orderRepository; 
    
    public ProductsService(
        ProductRepository productRepository,
        ProductMapper productMapper,
        ProductCategoryRepository productCategoryRepository,
        ProductSummaryMapper productSummaryMapper,
        WishlistService wishlistService,
        SecurityOutil securityOutil,
        CartRepository cartRepository,
        OrderRepository orderRepository
    ) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productCategoryRepository = productCategoryRepository;
        this.productSummaryMapper = productSummaryMapper;
        this.wishlistService = wishlistService;
        this.securityOutil = securityOutil;
        this.cartRepository = cartRepository; 
        this.orderRepository = orderRepository;
    }
    
    public ProductDTO getProductById(Long id) {
        Long userId = securityOutil.getAuthenticatedUserId(); 
        
        Product product = productRepository.findById(id).orElseThrow(() -> 
            new ProductNotFoundException("That product does not exist...")
        );

        ProductDTO productDto = productMapper.productToDTO(product);
        
        Boolean inWishlist = wishlistService.isProductInWishlist(userId, product.getId()); 
        
       Boolean inCart = cartRepository.existsByUserIdAndCartItemsProductId(userId, product.getId()); 
        
        
        productDto.setInWishlist(inWishlist); 
        productDto.setInCart(inCart); 
        return productDto; 
    }
    
    public List<ProductSummaryDTO> findProductsByCategory(String category) {
        // Check if the category exists
        boolean categoryExists = productCategoryRepository.existsByName(category); 
        if (!categoryExists) {
            // if not throw exception
            throw new CategoryNotFoundException("Please Try With Other Category.");
        }
        
        // find the category products 
        List<Product> products = productRepository.findByCategory_Name(category); 
        
        // return them with dto 
        return products.stream()
            .map(productSummaryMapper::toDto)
            .collect(Collectors.toList());
    }
    
    // verify if a product is purchased by a user
    public Boolean isPurchasedByUser(Long userId, Long productId) {
        return orderRepository.existsByUserIdAndProductId(userId, productId);
    }
    
    // get best sellers
    public List<BestSellerDTO> getBestSellers() {
    List<Object[]> results = orderRepository.findMostOrderedProducts();
    List<BestSellerDTO> bestSellerDTOList = new ArrayList<>();

    for (Object[] result : results) {
        Long productId = (Long) result[0];
        String title = (String) result[1];
        String pictures = (String) result[2];
        Long stock = (Long) result[3];
        BigDecimal price = (BigDecimal) result[4];
        Long selled = (Long) result[5];
        String[] picturesArray = pictures.split(",");  

        BestSellerDTO dto = new BestSellerDTO(productId, title, picturesArray, stock, price, selled);
        bestSellerDTOList.add(dto);
    }

    return bestSellerDTOList;
 }
    
    
}