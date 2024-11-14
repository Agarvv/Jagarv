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


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private final ProductRepository productRepository; 
    private final ProductMapper productMapper;
    private final ProductCategoryRepository productCategoryRepository; 
    private final ProductSummaryMapper productSummaryMapper; 
    
    public ProductsService(
        ProductRepository productRepository,
        ProductMapper productMapper,
        ProductCategoryRepository productCategoryRepository,
        ProductSummaryMapper productSummaryMapper
    ) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productCategoryRepository = productCategoryRepository;
        this.productSummaryMapper = productSummaryMapper;
    }
    
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> 
            new ProductNotFoundException("That product does not exist...")
        );

        return productMapper.productToDTO(product);
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
}