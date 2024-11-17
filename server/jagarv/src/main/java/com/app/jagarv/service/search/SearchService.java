package com.app.jagarv.service.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.app.jagarv.repository.product.ProductRepository;

import com.app.jagarv.entity.product.Product;
import com.app.jagarv.mapper.product.ProductMapper;
import com.app.jagarv.dto.product.read.ProductDTO;


@Service 
public class SearchService
{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    public SearchService
    (
        ProductRepository productRepository, ProductMapper productMapper 
    ) 
    
    {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> searchProductsByQuery(String query)
    {
        List<Product> products = productRepository.findProductsByTitle(query);
        
        return products.stream()
            .map(productMapper::productToDTO)
            .collect(Collectors.toList());
    }
}