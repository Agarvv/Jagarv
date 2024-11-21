package com.app.jagarv.service.products;

import org.springframework.stereotype.Service;

import com.app.jagarv.dto.product.create.CreateProductOpinionDTO;
import com.app.jagarv.exception.exceptions.products.ProductNotFoundException;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.repository.user.UserRepository;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.entity.product.ProductOpinion;
import com.app.jagarv.entity.user.User;
import com.app.jagarv.repository.product.ProductOpinionRepository; 

import com.app.jagarv.outil.SecurityOutil;
import com.app.jagarv.exception.exceptions.users.UserNotFoundException;
import com.app.jagarv.exception.exceptions.products.ProductNotPurchasedException;
import com.app.jagarv.service.products.ProductsService;


@Service
public class ProductOpinionService {

    private final ProductRepository productRepository;
    private final SecurityOutil securityOutil;
    private final UserRepository userRepository;
    private final ProductOpinionRepository productOpinionRepository;
    private final ProductsService productsService;

    public ProductOpinionService(
        ProductRepository productRepository,
         SecurityOutil securityOutil, 
         UserRepository userRepository, 
         ProductOpinionRepository productOpinionRepository,
         ProductsService productsService
        ) {
        this.productRepository = productRepository;
        this.securityOutil = securityOutil;
        this.userRepository = userRepository;
        this.productOpinionRepository = productOpinionRepository;
        this.productsService = productsService;
    }

    public void createProductOpinion(CreateProductOpinionDTO opinion) {
        
        Product product = productRepository.findById(opinion.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("That Product Does Not Exist..."));

        Long userId = securityOutil.getAuthenticatedUserId();

        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Please Try again later..."));
        
        if(!productsService.isPurchasedByUser(userId)) {
            throw new ProductNotPurchasedException("You have not purchased this product yet");
        }

        ProductOpinion newOpinion = new ProductOpinion();
        newOpinion.setContent(opinion.getContent());
        newOpinion.setProduct(product);
        newOpinion.setUser(user);

        productOpinionRepository.save(newOpinion);

      
    }
}