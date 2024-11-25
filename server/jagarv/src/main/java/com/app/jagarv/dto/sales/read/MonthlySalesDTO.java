package com.app.jagarv.dto.sales.read; 

public class MonthlySalesDTO {
    private String month;
    private Long totalSales;
    private Double totalAmount;

    public MonthlySalesDTO(String month, Long totalSales, Double totalAmount) {
        this.month = month;
        this.totalSales = totalSales;
        this.totalAmount = totalAmount;
    }

}