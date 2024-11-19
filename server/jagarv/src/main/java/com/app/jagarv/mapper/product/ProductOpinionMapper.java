package com.app.jagarv.mapper.product;

import com.app.jagarv.dto.product.read.ProductOpinionDTO;
import com.app.jagarv.dto.user.UserDTO;
import com.app.jagarv.entity.product.ProductOpinion;
import com.app.jagarv.entity.user.User;
import com.app.jagarv.mapper.user.UserMapper; 

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class}) 
public interface ProductOpinionMapper {

    ProductOpinionDTO opinionToDTO(ProductOpinion opinion);
}