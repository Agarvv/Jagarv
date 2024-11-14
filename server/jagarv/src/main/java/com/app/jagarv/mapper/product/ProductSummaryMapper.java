package com.app.jagarv.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.dto.product.read.ProductSummaryDTO;

@Mapper
public interface ProductSummaryMapper {

    ProductSummaryMapper INSTANCE = Mappers.getMapper(ProductSummaryMapper.class);

    ProductSummaryDTO toDto(Product product);

    Product fromDto(ProductSummaryDTO productSummaryDTO);
}