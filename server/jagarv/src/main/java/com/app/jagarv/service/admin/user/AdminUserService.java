package com.app.jagarv.service.admin.user;

import com.app.jagarv.dto.user.UserDTO;
import com.app.jagarv.mapper.user.UserMapper;
import com.app.jagarv.repository.user.UserRepository;

import org.springframework.stereotype.Service;

import com.app.jagarv.service.admin.ban.BanService;


import java.util.List;
import java.util.stream.Collectors;

// the admin user endpoints
@Service
public class AdminUserService {
    
    private final BanService banService; 
    private final UserRepository userRepository; 
    private final UserMapper userMapper;

    public AdminUserService(UserRepository userRepository, UserMapper userMapper, BanService banService) {
      this.userRepository = userRepository;
      this.userMapper = userMapper;
      this.banService = banService;
    }
    
    
    public List<UserDTO> getUsers() {
    return userRepository.findAll()
            .stream()
            .map
            (user -> 
            userMapper.userToDTO(user)
            .setIsBanned
            (banService.isBanned(user.getId())))
            .collect(Collectors.toList());
    }
    
    
}

