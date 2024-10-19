package com.app.jagarv.service.admin.sales;

import com.app.jagarv.repository.SalesRepository;
import com.app.jagarv.dto.sales.SalesDTO;
import com.app.jagarv.mapper.sales.SalesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

// The app's Sales service
@Service
public class SalesService {
    public List<SalesDTO> getSales() {
        // Injections
        @Autowired 
        private SalesRepisitory salesRepository; 
        
        @Autowired 
        private SalesMapper salesMapper; 
        
        // Returns all the app's sales to the controller
        public List<SalesDTO> getSales() {
            return salesRepository.findAll()           .stream()
            .map(salesMapper::salesToDTO)
            .collect(Collectors.toList());
        }
    }
}
