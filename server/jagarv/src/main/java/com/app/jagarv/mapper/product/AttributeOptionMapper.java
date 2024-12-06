package com.app.jagarv.mapper.product;

import org.mapstruct.Mapper;
import com.app.jagarv.entity.product.AttributeOption;
import com.app.jagarv.dto.product.read.AttributeOptionDTO;

@Mapper(componentModel = "spring")
public interface AttributeOptionMapper {
    AttributeOptionDTO toDTO(AttributeOption attributeOption);
}