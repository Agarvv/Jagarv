package com.app.jagarv.mapper.cart;

import com.app.jagarv.dto.cart.read.CartDTO;
import com.app.jagarv.dto.cart.read.CartItemDTO;
import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.entity.cart.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")  
public interface CartMapper {
    @Mapping(source = "id", target = "cartId")
    @Mapping(source = "cartItems", target = "items")
    CartDTO toDto(Cart cart);

    List<CartItemDTO> cartItemsToCartItemDTO(List<CartItem> cartItems);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.title", target = "title")
    @Mapping(source = "product.price", target = "price")      
    @Mapping(source = "product.pictures", target = "pictures")  
    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);
}