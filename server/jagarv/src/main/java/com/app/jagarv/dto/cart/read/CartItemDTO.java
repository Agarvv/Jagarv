package com.app.jagarv.dto.cart.read;

import java.util.List;
import java.math.BigDecimal; 
import com.app.jagarv.dto.product.read.AttributeOptionDTO;

public class CartItemDTO {
   
   private Long id; // product id
   private String title;
   private BigDecimal price; 
   private Long quantity;
   private List<AttributeOptionDTO> options; 
   private List<String> pictures; 

   public CartItemDTO(Long productId, String title, BigDecimal price, Long quantity, List<AttributeOptionDTO> options, List<String> pictures) {
       this.id = productId;
       this.title = title;
       this.price = price; 
       this.quantity = quantity;
       this.options = options;
       this.pictures = pictures; 
   }
   
   public Long getProductId() {
       return id;
   }

   public void setProductId(Long productId) {
       this.id = productId;
   }

   public String getTitle() {
       return title;
   }

   public void setTitle(String title) {
       this.title = title;
   }

   public Long getQuantity() {
       return quantity;
   }

   public void setQuantity(Long quantity) {
       this.quantity = quantity;
   }

   public List<AttributeOptionDTO> getOptions() { 
       return options;
   }

   public void setOptions(List<AttributeOptionDTO> options) {
       this.options = options;
   }
   
   public BigDecimal getPrice() {
       return price;
   }

   public void setPrice(BigDecimal price) {
       this.price = price;
   }
   
   public List<String> getPictures() {
       return pictures; 
   }
   
   public void setPictures(List<String> pictures) {
       this.pictures = pictures; 
   }
}