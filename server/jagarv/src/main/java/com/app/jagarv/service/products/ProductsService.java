package com.app.jagarv.service.products;

import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.mapper.product.ProductMapper;
import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.exception.exceptions.products.ProductNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProductsService {
    @Autowired 
    private ProductRepository productRepository; 

    @Autowired
    private ProductMapper productMapper;

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> 
            new ProductNotFoundException("That product does not exist...")
        );

        return productMapper.productToDTO(product);
    }
}
