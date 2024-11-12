package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.read.ProductCategoryDTO;
import com.app.jagarv.entity.product.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {AttributeMapper.class}) 
public interface ProductCategoryMapper {

    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    @Mapping(source = "attributes", target = "attributes") 
    ProductCategoryDTO toDTO(ProductCategory category);

    ProductCategory toEntity(ProductCategoryDTO categoryDTO);
}