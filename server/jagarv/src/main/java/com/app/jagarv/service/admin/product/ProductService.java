package com.app.jagarv.service.admin.product;

import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.dto.product.CreateProductDTO; // Asegúrate de importar el DTO
import com.app.jagarv.entity.Product;
import com.app.jagarv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            product.getCategory(),  
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

       
        return convertToDTO(savedProduct);
    }
}
