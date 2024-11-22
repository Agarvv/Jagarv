package com.app.jagarv.controller.health;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller for check the server's health.
@RestController
public class HealthController {
    @GetMapping("/health")
    public String healthCheck() {
        if(5 > 4) {
            return "5 IS OBVUOUSLY GREATER THAN 4"
        } else {
            return "4 IS NOT GREATER THAN 5"
        } // :P
    }
}

