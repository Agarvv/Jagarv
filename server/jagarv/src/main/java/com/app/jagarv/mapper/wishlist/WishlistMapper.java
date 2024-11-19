package com.app.jagarv.mapper.wishlist;

import com.app.jagarv.dto.wishlist.WishlistDTO;
import com.app.jagarv.dto.product.read.ProductDTO;
import com.app.jagarv.entity.wishlist.Wishlist;
import com.app.jagarv.entity.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class);

    @Mapping(source = "products", target = "products")
    WishlistDTO toDto(Wishlist wishlist);
    
    ProductDTO productToProductDTO(Product product);
}