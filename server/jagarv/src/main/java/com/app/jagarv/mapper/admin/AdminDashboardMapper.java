/* 
package com.app.jagarv.mapper.admin;

import com.app.jagarv.dto.product.read.MostOrderedProductDTO;
import com.app.jagarv.dto.sales.read.MonthlySalesDTO;
import com.app.jagarv.dto.admin.dashboard.AdminDashboardDTO;
import com.app.jagarv.entity.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminDashboardMapper {

    @Mapping(target = "title", source = "products.title")
    @Mapping(target = "timesOrdered", source = "orderItems.quantity")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "stock", source = "product.stock")
    MostOrderedProductDTO toMostOrderedProductDTO(Order order);

    @Mapping(target = "title", source = "0")
    @Mapping(target = "timesOrdered", source = "1")
    @Mapping(target = "price", source = "2")
    @Mapping(target = "stock", source = "3")
    MostOrderedProductDTO toMostOrderedProductDTO(Object[] data);

    @Mapping(target = "month", source = "0")
    @Mapping(target = "totalSales", source = "1")
    @Mapping(target = "totalAmount", source = "2")
    MonthlySalesDTO toMonthlySalesDTO(Object[] data);
}
*/
