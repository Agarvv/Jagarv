package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.entity.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ProductCategoryMapper.class, ProductVariantMapper.class})
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "category", target = "category")
    @Mapping(source = "variants", target = "variants")
    ProductDTO productToDTO(Product product);

    @Mapping(source = "category", target = "category")
    @Mapping(source = "variants", target = "variants")
    Product dtoToProduct(ProductDTO productDTO);
}