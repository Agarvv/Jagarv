package com.app.jagarv.controller.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import org.springframework.http.ResponseEntity;

//Controller for DB health check
@RestController
public class DatabaseHealthController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/database/health") 
    public ResponseEntity<String> checkDatabaseHealth() {  
        try {
            dataSource.getConnection().close();  
            return ResponseEntity.ok("Database connection is healthy");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Database connection is unhealthy: " + e.getMessage());
        }
    }
}
