package com.app.jagarv.service.user; 

import org.springframework.stereotype.Service;
import com.app.jagarv.dto.user.UserDTO; 
import com.app.jagarv.entity.user.User; 
import com.app.jagarv.outil.SecurityOutil;
import com.app.jagarv.repository.user.UserRepository; 
import com.app.jagarv.exception.exceptions.users.UserNotFoundException; 
import com.app.jagarv.dto.user.SetUserProfilePicDTO;
import com.app.jagarv.mapper.user.UserMapper; 
import com.app.jagarv.dto.user.SetUserAdressDTO; 

@Service 
public class UserService
{
    private final UserRepository userRepository;
    private final SecurityOutil securityOutil;
    private final UserMapper userMapper; 
    
    public UserService
    (
      UserRepository userRepository,
      SecurityOutil securityOutil,
      UserMapper userMapper 
    )
    {
        this.userRepository = userRepository;
        this.securityOutil = securityOutil; 
        this.userMapper = userMapper; 
    }
    
    public UserDTO getUserData() 
    {
        User user = findAuthenticatedUser(); 
        return userMapper.userToDTO(user); 
    }
    
    public void setUserProfilePicture(SetUserProfilePicDTO data)
    {
        User user = findAuthenticatedUser(); 
        
        user.setProfilePicture(data.getProfilePicture()); 
        
        userRepository.save(user); 
    }
    
    public void setUserAdress(SetUserAdressDTO adress) 
    {
        User user = findAuthenticatedUser(); 
        user.setAdress(adress.getAdress()); 
        userRepository.save(user); 
    }
    
    public User findAuthenticatedUser() 
    {
        Long userId = securityOutil.getAuthenticatedUserId();
        
        User user = userRepository.findById(userId).orElseThrow(() 
         -> new UserNotFoundException("User not Found...")
        );
        
        return user; 
    }
}