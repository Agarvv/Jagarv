package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.read.ProductDTO;
import com.app.jagarv.entity.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductCategoryMapper.class, ProductVariantMapper.class, ProductOpinionMapper.class})
public interface ProductMapper {

    @Mapping(source = "category", target = "category")
    @Mapping(source = "variants", target = "variants")
    @Mapping(source = "opinions", target = "opinions") 
    ProductDTO productToDTO(Product product);

    @Mapping(source = "category", target = "category")
    @Mapping(source = "variants", target = "variants")
    @Mapping(source = "opinions", target = "opinions") 
    Product dtoToProduct(ProductDTO productDTO);
}