package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.AttributeDTO;
import com.app.jagarv.entity.product.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AttributeMapper {

    AttributeMapper INSTANCE = Mappers.getMapper(AttributeMapper.class);

    AttributeDTO toDTO(Attribute attribute);

    Attribute toEntity(AttributeDTO attributeDTO);
}