package com.app.jagarv.mapper.product;

import org.mapstruct.Mapper;
import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.entity.Product;

@Mapper(componentModel = "spring") 
public interface ProductMapper { 
    // Entity to DTO 
    ProductDTO productToDTO(Product product);
    
    // DTO to Entity 
    Product dtoToProduct(ProductDTO productDTO); 
}

