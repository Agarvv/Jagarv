package com.app.jagarv.mapper.wishlist;

import com.app.jagarv.dto.wishlist.WishlistDTO;
import com.app.jagarv.entity.wishlist.Wishlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
    WishlistDTO toDto(Wishlist wishlist);
}