package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.read.ProductOpinionDTO;
import com.app.jagarv.entity.product.ProductOpinion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductOpinionMapper {

    ProductOpinionMapper INSTANCE = Mappers.getMapper(ProductOpinionMapper.class);

    ProductOpinionDTO productOpinionToDTO(ProductOpinion opinion);
}