package com.app.jagarv.entity.product;

import com.app.jagarv.entity.cart.CartItem;
import jakarta.persistence.*;
import com.app.jagarv.entity.order.Order; 


@Entity
@Table(name = "order_products_cart_items")
public class OrderProductsCartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}