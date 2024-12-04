package com.app.jagarv.repository.order;

import com.app.jagarv.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT COUNT(o) FROM Order o WHERE CAST(o.date AS java.sql.Date) = CURRENT_DATE")
    Long countOrdersToday();

    @Query(value = "SELECT SUM(amount) FROM orders WHERE date::DATE = CURRENT_DATE", nativeQuery = true)
    Long getTotalEarningsToday();

    @Query(value = "SELECT p.id, p.title, p.pictures, p.stock, p.price, COUNT(opci.cart_item_id) AS order_count " +
               "FROM products p " +
               "JOIN cart_item ci ON ci.product_id = p.id " +
               "JOIN order_products_cart_items opci ON opci.cart_item_id = ci.id " +
               "JOIN orders o ON o.id = opci.order_id " +
               "GROUP BY p.id, p.title " +
               "ORDER BY order_count DESC", 
       nativeQuery = true)
    List<Object[]> findMostOrderedProducts();

    @Query(value = "SELECT EXTRACT(MONTH FROM date::DATE) AS month, COUNT(*) AS order_count " +
                   "FROM orders " +
                   "WHERE EXTRACT(MONTH FROM date::DATE) BETWEEN 7 AND 12 " +
                   "GROUP BY month " +
                   "ORDER BY month", nativeQuery = true)
    List<Object[]> getOrderCountByMonth();
    
    @Query(value = "SELECT SUM(amount) FROM orders", nativeQuery = true)
    Long getTotalIncome();
    
    
    List<Order> findAllByUser_Id(Long userId);

    Boolean existsByUserIdAndProducts_Id(Long userId, Long productId);
}