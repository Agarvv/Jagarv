package com.app.jagarv.outil; 

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.app.jagarv.entity.product.Product; 

import com.app.jagarv.repository.product.ProductRepository; 

public class ProductOutil {
    
    private final ProductRepository productRepository; 
    
    public ProductOutil(ProductRepository productRepository) {
        this.productRepository = productRepository; 
    }
    
    
    // find products given a list Of Ids
    public Map<Long, Product> findAllById(List<Long> ids) {
        
        List<Product> products = productRepository.findAllById(ids);

            Map<Long, Product> productMap = products.stream()
                    .collect(Collectors.toMap(Product::getId, product -> product));
                    
            return productMap; 
    }
}