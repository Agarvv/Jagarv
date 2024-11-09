package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.ProductCategoryDTO;
import com.app.jagarv.entity.product.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    ProductCategoryDTO toDTO(ProductCategory category);

    ProductCategory toEntity(ProductCategoryDTO categoryDTO);
}
