package com.app.jagarv.service;

import com.app.jagarv.dto.ProductDTO;
import com.app.jagarv.entity.Product;
import com.app.jagarv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service 
public class ProductService {
    
    @Autowired 
    private ProductRepository productRepository;  
    
    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getTitle(),
            product.getDescription(),
            product.getMain_picture(),  
            product.getPrice()
        );  
    }
    
    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
        .stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    }
}