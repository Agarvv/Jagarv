package com.app.jagarv.service.admin.product;

import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.dto.product.CreateProductDTO;
import com.app.jagarv.entity.Product;
import com.app.jagarv.repository.ProductRepository;
import com.app.jagarv.mapper.product.ProductMapper;
import com.app.jagarv.exception.exceptions.products.ProductAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

@Service 
public class ProductService {
    
    // INJECTIONS
    @Autowired 
    private ProductRepository productRepository;  
    
    @Autowired
    private ProductMapper productMapper; 

    // RETURNS ALL THE PRODUCTS FROM THE DB TO THE CONTROLLER
    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
            .stream()
            .map(productMapper::productToDTO)
            .collect(Collectors.toList());
    }
 
 
    // creates a new product
    public ProductDTO createProduct(CreateProductDTO createProductDTO) {
    // Products title should be unique.
    if (productRepository.existsByTitle(createProductDTO.getTitle())) {
        throw new ProductAlreadyExistsException("The Product Already Exists, Try with another Title.");
    }

    Product product = new Product();
    product.setTitle(createProductDTO.getTitle());
    product.setDescription(createProductDTO.getDescription());
    product.setCategory(createProductDTO.getCategory());
    product.setPrice(createProductDTO.getPrice());
    product.setPictures(Arrays.asList(createProductDTO.getPictures()));
    Product savedProduct = productRepository.save(product);
    return productMapper.productToDTO(savedProduct);
}
    
    
    
    
}