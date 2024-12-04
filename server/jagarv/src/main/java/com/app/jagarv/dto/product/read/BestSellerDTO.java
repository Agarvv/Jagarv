package com.app.jagarv.dto.product.read;

public class BestSellerDTO {
    private Long id;
    private String title;
    private String pictures;  
    private Integer stock;
    private Double price;
    private Long selled;

    public BestSellerDTO(Long id, String title, String pictures, Integer stock, Double price, Long selled) {
        this.id = id;
        this.title = title;
        this.pictures = pictures;
        this.stock = stock;
        this.price = price;
        this.selled = selled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getSelled() {
        return selled;
    }

    public void setSelled(Long selled) {
        this.selled = selled;
    }
}