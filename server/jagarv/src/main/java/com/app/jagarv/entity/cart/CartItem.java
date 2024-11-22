package com.app.jagarv.entity.cart;

import jakarta.persistence.*;
import java.util.List;

import com.app.jagarv.entity.product.AttributeOption;
import com.app.jagarv.entity.product.Product;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Long quantity;

    @ManyToMany
    @JoinTable(
        name = "cart_item_attribute_option",
        joinColumns = @JoinColumn(name = "cart_item_id"),
        inverseJoinColumns = @JoinColumn(name = "attribute_option_id"))
    private List<AttributeOption> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public List<AttributeOption> getOptions() {
        return options;
    }

    public void setOptions(List<AttributeOption> options) {
        this.options = options;
    }
}