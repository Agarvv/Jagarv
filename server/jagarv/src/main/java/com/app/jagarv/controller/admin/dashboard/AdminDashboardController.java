package com.app.jagarv.controller.admin.dashboard; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.app.jagarv.service.admin.dashboard.AdminDashboardService; 
import com.app.jagarv.dto.admin.dashboard.AdminDashboardDTO; 

@RestController
@RequestMapping("/admin/dashboard")
public class AdminDashboardController {
    private final AdminDashboardService adminDashboardService; 
    
    public AdminDashboardController(AdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }
    
    @GetMapping
    public ResponseEntity<AdminDashboardDTO> getAdminDashboard() {
        AdminDashboardDTO dashboardData = adminDashboardService.getAdminDashboard();
        return ResponseEntity.ok(dashboardData); 
    }
}