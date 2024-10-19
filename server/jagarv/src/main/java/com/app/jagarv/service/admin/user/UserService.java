package com.app.jagarv.service.admin.user;

import com.app.jagarv.dto.user.UserDTO;
import com.app.jagarv.repository.UserRepository;
import com.app.jagarv.mapper.user.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired 
    private UserRepository userRepository; 
    
    @Autowired 
    private UserMapper userMapper;
    
    
    public List<UserDTO> getUsers() {
        return userRepository.findAll()           .stream()
            .map(userMapper::userToDTO)
            .collect(Collectors.toList());
    }
}
