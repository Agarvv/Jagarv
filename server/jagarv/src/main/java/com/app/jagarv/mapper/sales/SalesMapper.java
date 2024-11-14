package com.app.jagarv.mapper.sales; 

import org.mapstruct.Mapper;

import com.app.jagarv.dto.sales.read.SalesDTO;
import com.app.jagarv.entity.sale.Sale;

@Mapper(componentModel = "spring") 
public interface SalesMapper {
    //Entity to DTO 
    SalesDTO salesToDTO(Sale sale);
    
    //DTO to Entity 
    Sale dtoToSales(SalesDTO salesDTO); 
}
