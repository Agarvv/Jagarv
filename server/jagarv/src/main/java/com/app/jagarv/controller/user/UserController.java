package com.app.jagarv.controller.user; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.jagarv.dto.user.UserDTO; 
import com.app.jagarv.service.user.UserService; 
import com.app.jagarv.dto.user.SetUserProfilePicDTO;
import com.app.jagarv.dto.ApiResponse; 


import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/jagarv/user")
public class UserController
{
    private final UserService userService; 
    
    public UserController
    (
     UserService userService
    )
    {
        this.userService = userService; 
    }
    
    @GetMapping 
    public ResponseEntity<UserDTO> 
    getUserData() 
    {
        UserDTO user =  userService.getUserData(); 
        return ResponseEntity.ok(user); 
    }
    
    @PostMapping("/setProfilePicture")
    public ResponseEntity<ApiResponse<Void>> 
    setUserProfilePicture(@Valid SetUserProfilePicDTO user)
    {
        userService.setUserProfilePicture(user);
        
        return ResponseEntity.ok(new ApiResponse<>(user.getProfilePicture(), null)); 
    }
}