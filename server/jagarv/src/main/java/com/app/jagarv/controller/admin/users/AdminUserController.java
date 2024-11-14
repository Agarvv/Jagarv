package com.app.jagarv.controller.admin.users;

import com.app.jagarv.dto.user.UserDTO;
import com.app.jagarv.service.admin.user.AdminUserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController 
@RequestMapping("/admin/users")
public class AdminUserController {
   
   @Autowired 
   private AdminUserService userService; 
   
   // gets all the users of the app for the admins
   @GetMapping
   public List<UserDTO> getUsers() {
       return userService.getUsers();
   }
}

