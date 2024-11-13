package com.app.jagarv.service.products;

import org.springframework.stereotype.Service;

import com.app.jagarv.dto.product.create.CreateProductOpinionDTO;
import com.app.jagarv.exception.exceptions.products.ProductNotFoundException;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.repository.UserRepository;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.entity.User;
import com.app.jagarv.entity.product.ProductOpinion;
import com.app.jagarv.repository.ProductOpinionRepository;
import com.app.jagarv.outil.SecurityOutil;
import com.app.jagarv.exception.exceptions.users.UserIdNotValidException;
import com.app.jagarv.exception.exceptions.users.UserNotFoundException;

@Service
public class ProductOpinionService {

    private final ProductRepository productRepository;
    private final SecurityOutil securityOutil;
    private final UserRepository userRepository;
    private final ProductOpinionRepository productOpinionRepository;

    public ProductOpinionService(ProductRepository productRepository, SecurityOutil securityOutil, UserRepository userRepository, ProductOpinionRepository productOpinionRepository) {
        this.productRepository = productRepository;
        this.securityOutil = securityOutil;
        this.userRepository = userRepository;
        this.productOpinionRepository = productOpinionRepository;
    }

    public void createProductOpinion(CreateProductOpinionDTO opinion) {
        
        Product product = productRepository.findById(opinion.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("That Product Does Not Exist..."));

        Long userId = securityOutil.getAuthenticatedUserId();
        if (userId == null) {
            throw new UserIdNotValidException("Please try to log in or try again later...");
        }

        User user = userRepository.findById(securityOutil.getAuthenticatedUserId()).orElseThrow(() ->
                new UserNotFoundException("Please Try again later..."));

        ProductOpinion newOpinion = new ProductOpinion();
        newOpinion.setContent(opinion.getContent());
        newOpinion.setProduct(product);
        newOpinion.setUser(user);

        productOpinionRepository.save(newOpinion);

        // i will add also if user purchased product validations here, but when orders and cart system is finished
    }
}