package com.app.jagarv.service.admin.product;

import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.dto.product.CreateProductDTO;
import com.app.jagarv.entity.Product;
import com.app.jagarv.repository.ProductRepository;
import com.app.jagarv.mapper.product.ProductMapper; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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
    
    // CREATES A NEW PRODUCT
    public ProductDTO createProduct(CreateProductDTO createProductDTO) {
    
        if (productRepository.existsByTitle(createProductDTO.getTitle())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists.");
        }
        
        

    
        Product product = new Product();
        product.setTitle(createProductDTO.getTitle());
        product.setDescription(createProductDTO.getDescription());
        product.setCategory(createProductDTO.getCategory());
        product.setMain_picture(createProductDTO.getMain_picture());  
        product.setPrice(createProductDTO.getPrice());

        Product savedProduct = productRepository.save(product);

    
        return productMapper.productToDTO(savedProduct);
    }
    
    
}