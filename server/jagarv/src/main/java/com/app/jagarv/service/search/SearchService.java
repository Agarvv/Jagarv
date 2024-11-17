package com.app.jagarv.service.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.app.jagarv.dto.product.read.ProductSummaryDTO;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.mapper.product.ProductSummaryMapper;
import com.app.jagarv.entity.product.Product;

@Service 
public class SearchService
{

    private final ProductRepository productRepository;
    private final ProductSummaryMapper productSummaryMapper;
    
    public SearchService
    (
        ProductRepository productRepository, ProductSummaryMapper productSummaryMapper
    ) 
    
    {
        this.productRepository = productRepository;
        this.productSummaryMapper = productSummaryMapper;
    }

    public List<ProductSummaryDTO> searchProductsByQuery(String query)
    {
        List<Product> products = productRepository.findProductsByTitle(query);
        
        return products.stream()
            .map(productSummaryMapper::toDto)
            .collect(Collectors.toList());
    }
}