package com.app.jagarv.dto.product.read; 

public class MostOrderedProductDTO {
    private String title;
    private Long timesOrdered;
    private Double price;
    private Long stock;


    public MostOrderedProductDTO(String title, Long timesOrdered, Double price, Long stock) {
        this.title = title;
        this.timesOrdered = timesOrdered;
        this.price = price;
        this.stock = stock;
    }

}