package com.app.jagarv.service.admin.sales;

import com.app.jagarv.dto.sales.read.SalesDTO;
import com.app.jagarv.mapper.sales.SalesMapper;
import com.app.jagarv.repository.sale.SalesRepository;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

// The app's Sales service
@Service
public class AdminSalesService {
    // Injections
    private final SalesRepository salesRepository;
    private final SalesMapper salesMapper;

    public AdminSalesService(SalesRepository salesRepository, SalesMapper salesMapper) {
      this.salesRepository = salesRepository;
      this.salesMapper = salesMapper;
    }
     // Returns all the app's sales to the controller
    public List<SalesDTO> getSales() {
        return salesRepository.findAll()
        .stream()
        .map(salesMapper::salesToDTO)
        .collect(Collectors.toList());
       
    }
}
