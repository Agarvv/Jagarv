package com.app.jagarv.mapper.cart;

import com.app.jagarv.dto.cart.read.CartDTO;
import com.app.jagarv.dto.cart.read.CartItemDTO;
import com.app.jagarv.dto.product.read.AttributeOptionDTO;
import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.entity.cart.CartItem;
import com.app.jagarv.entity.product.AttributeOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "id", target = "cartId")
    @Mapping(source = "cartItems", target = "items")
    CartDTO toDto(Cart cart);

    List<CartItemDTO> cartItemsToCartItemDTO(List<CartItem> cartItems);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.title", target = "title")
    @Mapping(source = "product.price", target = "price")
    @Mapping(source = "options", target = "options", qualifiedByName = "mapOptions")
    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    @Named("mapOptions")
    default List<AttributeOptionDTO> mapOptions(List<AttributeOption> options) {
        return options.stream()
                .map(option -> new AttributeOptionDTO(
                        option.getId(),
                        option.getValue(),
                        option.getAttribute().getName()
                ))
                .collect(Collectors.toList());
    }
}