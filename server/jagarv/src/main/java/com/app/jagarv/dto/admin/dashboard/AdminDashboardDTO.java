package com.app.jagarv.dto.admin.dashboard; 

import com.app.jagarv.dto.product.read.MostOrderedProductDTO; 
import com.app.jagarv.dto.sales.read.MonthlySalesDTO; 


public class AdminDashboardDTO {
    private Long totalOrdersToday;
    private Long totalAmountToday;
    private Long newUsersToday;
    private List<MonthlySalesDTO> monthlySales;
    private List<MostOrderedProductDTO> mostOrderedProducts;

    public AdminDashboardDTO
    (Long totalOrdersToday,
    Long totalAmountToday,
    Long newUsersToday,
    List<MonthlySalesDTO> monthlySales,
    List<MostOrderedProductDTO> mostOrderedProducts
    ) 
    {
        this.totalOrdersToday = totalOrdersToday;
        this.totalAmountToday = totalAmountToday;
        this.newUsersToday = newUsersToday;
        this.monthlySales = monthlySales;
        this.mostOrderedProducts = mostOrderedProducts;
    }

}