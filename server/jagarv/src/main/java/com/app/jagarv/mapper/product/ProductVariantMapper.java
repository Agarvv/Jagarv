package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.ProductVariantDTO;
import com.app.jagarv.dto.product.AttributeDTO;
import com.app.jagarv.dto.product.AttributeOptionDTO;
import com.app.jagarv.entity.product.ProductVariant;
import com.app.jagarv.entity.product.AttributeOption;
import com.app.jagarv.entity.product.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {

    ProductVariantMapper INSTANCE = Mappers.getMapper(ProductVariantMapper.class);

    @Mapping(source = "attributeOptions", target = "attributes") 
    @Mapping(source = "images", target = "images")
    ProductVariantDTO toDTO(ProductVariant variant);

    @Mapping(source = "attributes", target = "attributeOptions")  
    @Mapping(source = "images", target = "images")
    ProductVariant toEntity(ProductVariantDTO variantDTO);

    default List<AttributeDTO> mapAttributeOptionsToAttributes(List<AttributeOption> attributeOptions) {
        Map<String, List<AttributeOptionDTO>> groupedOptions = attributeOptions.stream()
                .collect(Collectors.groupingBy(
                        option -> option.getAttribute().getName(),
                        Collectors.mapping(
                                option -> new AttributeOptionDTO(option.getId(), option.getValue()),
                                Collectors.toList()
                        )
                ));

        return groupedOptions.entrySet().stream()
                .map(entry -> {
                    AttributeDTO attributeDTO = new AttributeDTO();
                    attributeDTO.setName(entry.getKey());
                    attributeDTO.setOptions(entry.getValue());
                    return attributeDTO;
                })
                .collect(Collectors.toList());
    }
    
    
    default List<AttributeOption> mapAttributesToAttributeOptions(List<AttributeDTO> attributes) {
    return attributes.stream()
            .flatMap(attributeDTO -> attributeDTO.getOptions().stream()
                    .map(optionDTO -> {
    
                        AttributeOption option = new AttributeOption();
                        option.setId(optionDTO.getId());
                        option.setValue(optionDTO.getValue());

                        Attribute attribute = new Attribute();
                        attribute.setName(attributeDTO.getName());
                        option.setAttribute(attribute);

                        return option;
                    })
            )
            .collect(Collectors.toList());
}

}