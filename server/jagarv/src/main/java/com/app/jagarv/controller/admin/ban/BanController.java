package com.app.jagarv.controller.admin.ban; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.app.jagarv.dto.ApiResponse;
import com.app.jagarv.service.admin.ban.BanService;
import com.app.jagarv.dto.user.BanUserDTO; 

@RestController 
@RequestMapping("/api/jagarv/admin/users/ban")
public class BanController 
{
    private final BanService banService; 
    
    public BanController 
    (
      BanService banService
    )
    {
        this.banService = banService; 
    }
    
    @PostMapping 
    public ResponseEntity<ApiResponse<Void>> 
    banOrUnbanUser(@Valid @RequestBody BanUserDTO ban) 
    {
        // message can be 'banned' or 'unbanned'
        String message = banService.banOrUnbanUser(ban); 
        return ResponseEntity.ok(new ApiResponse<>(message, null)); 
    }
    
}