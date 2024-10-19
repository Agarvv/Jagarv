package com.app.jagarv.mapper.user;

import org.mapstruct.Mapper; 
import com.app.jagarv.dto.user.UserDTO; 
import com.app.jagarv.entity.User; 


@Mapper(componentModel = "spring") 
public interface UserMapper {
    // Entity to DTO 
    UserDTO userToDTO(User user); 
    
    //DTO to Entity 
    User dtoToUser(UserDTO userDTO);
}