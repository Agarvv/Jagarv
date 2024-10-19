package com.app.jagarv.controller.admin.users;

import com.app.jagarv.dto.user.UserDTO;
import com.app.jagarv.service.admin.user.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController 
@RequestMapping("/admin/users")
public class UserController {
   
   @Autowired 
   private UserService userService; 
   
   
   public List<UserDTO> getUsers() {
       return userService.getUsers();
   }
}

