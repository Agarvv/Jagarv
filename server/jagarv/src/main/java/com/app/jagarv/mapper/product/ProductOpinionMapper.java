package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.read.ProductOpinionDTO;
import com.app.jagarv.entity.product.ProductOpinion;
import com.app.jagarv.mapper.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.app.jagarv.dto.user.UserDTO;
import com.app.jagarv.entity.user.User;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ProductOpinionMapper {

    ProductOpinionMapper INSTANCE = Mappers.getMapper(ProductOpinionMapper.class);
    
    @Mapping(source = "user", target = "user")
    ProductOpinionDTO productOpinionToDTO(ProductOpinion opinion);
    
    UserDTO userToDTO(User user); 
}