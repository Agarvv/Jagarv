package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.ProductVariantDTO;
import com.app.jagarv.entity.product.ProductVariant;
import com.app.jagarv.entity.product.AttributeOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {

    ProductVariantMapper INSTANCE = Mappers.getMapper(ProductVariantMapper.class);

    @Mapping(source = "attributeOptions", target = "attributeOptions")
    @Mapping(source = "images", target = "images")
    ProductVariantDTO toDTO(ProductVariant variant);

    @Mapping(source = "attributeOptions", target = "attributeOptions")
    @Mapping(source = "images", target = "images")
    ProductVariant toEntity(ProductVariantDTO variantDTO);

   
    default List<String> mapAttributeOptionsToDTO(List<AttributeOption> attributeOptions) {
        return attributeOptions.stream()
                .map(AttributeOption::getValue)  
                .collect(Collectors.toList());
    }

   
    default List<AttributeOption> mapAttributeOptionsToEntity(List<String> attributeOptions) {
        return attributeOptions.stream()
                .map(name -> {
                    AttributeOption option = new AttributeOption();
                    option.setValue(name);
                    return option;
                })
                .collect(Collectors.toList());
    }
}
