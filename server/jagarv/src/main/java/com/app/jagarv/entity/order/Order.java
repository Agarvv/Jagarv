package com.app.jagarv.entity.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.app.jagarv.entity.product.Product;
import com.app.jagarv.entity.user.User;
import com.app.jagarv.entity.cart.CartItem;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private User user;

    @ManyToMany
    @JoinTable(
        name = "order_product", 
        joinColumns = @JoinColumn(name = "order_id"), 
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<CartItem> products;

    // @NotBlank(message = "Status cannot be blank")
   // @Size(max = 20, message = "Status cannot exceed 20 characters")
    private String status;
    
  //  @NotNull
    private Long amount;

   // @NotNull 
    private String paymentId; 
    
   // @NotBlank
    private String adress;
    
    private String date; 
    
    private String method; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId; 
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getAdress() {
        return adress;
    }
    
    public void setAdress(String adress) {
        this.adress = adress; 
    }
    
    public String getDate() {
        return date; 
    }
    
    public void setDate(String date) {
        this.date = date; 
    }
    
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
}
