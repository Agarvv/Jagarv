package com.app.jagarv.repository.order;

import com.app.jagarv.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.entity.order.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE CAST(o.date AS java.sql.Date) = CURRENT_DATE")
    List<Order> findOrdersToday();

    @Query(value = "SELECT SUM(amount) FROM orders WHERE date::DATE = CURRENT_DATE", nativeQuery = true)
    Long getTotalEarningsToday();

    @Query(value = "SELECT p.* FROM products p " +
               "JOIN order_product op ON op.product_id = p.id " +
               "JOIN orders o ON o.id = op.order_id " +
               "GROUP BY p.id ORDER BY COUNT(o.id) DESC", nativeQuery = true)
    List<Product> findMostOrderedProducts();
    
    @Query(value = "SELECT EXTRACT(MONTH FROM date::DATE) AS month, COUNT(*) AS order_count " +
               "FROM orders " +
               "WHERE EXTRACT(MONTH FROM date::DATE) BETWEEN 7 AND 12 " +
               "GROUP BY month " +
               "ORDER BY month", nativeQuery = true)
    List<Object[]> getOrderCountByMonth();
   
    
    
    List<Order> findAllByUser_Id(Long userId);

    Boolean existsByUserIdAndProducts_Id(Long userId, Long productId);
}