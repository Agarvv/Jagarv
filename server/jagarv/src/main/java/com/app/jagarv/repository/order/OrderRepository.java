package com.app.jagarv.repository.order;

import com.app.jagarv.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.jagarv.dto.product.read.BestSellerDTO;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT COUNT(o) FROM Order o WHERE CAST(o.date AS java.sql.Date) = CURRENT_DATE")
    Long countOrdersToday();

    @Query(value = "SELECT SUM(amount) FROM orders WHERE date::DATE = CURRENT_DATE", nativeQuery = true)
    Long getTotalEarningsToday();

    @Query(value = 
    "SELECT p.id AS productId, " +
    "p.title AS title, " +
    "STRING_AGG(pp.pictures, ',') AS pictures, " + 
    "p.stock AS stock, " + 
    "p.price AS price, " + 
    "COUNT(opci.cart_item_id) AS ordersCount " +
    "FROM products p " +  
    "JOIN cart_item ci ON p.id = ci.product_id " +  
    "JOIN order_products_cart_items opci ON ci.id = opci.cart_item_id " +
    "JOIN orders o ON opci.order_id = o.id " +
    "JOIN product_pictures pp ON p.id = pp.product_id " +  
    "GROUP BY p.id, p.title, p.stock, p.price " + 
    "ORDER BY COUNT(opci.cart_item_id) DESC", nativeQuery = true)
    List<Object[]> findMostOrderedProducts();

    @Query(value = "SELECT EXTRACT(MONTH FROM date::DATE) AS month, COUNT(*) AS order_count " +
                   "FROM orders " +
                   "WHERE EXTRACT(MONTH FROM date::DATE) BETWEEN 7 AND 12 " +
                   "GROUP BY month " +
                   "ORDER BY month", nativeQuery = true)
    List<Object[]> getOrderCountByMonth();
    
    @Query(value = "SELECT SUM(amount) FROM orders", nativeQuery = true)
    Long getTotalIncome();
    
    
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findOrdersByUserId(@Param("userId") Long userId);
    

    Boolean existsByUserIdAndProducts_Id(Long userId, Long productId);
    
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END " +
           "FROM Order o " +
           "JOIN o.products ci " +
           "JOIN ci.product p " +
           "WHERE o.user.id = :userId AND p.id = :productId")
    boolean existsByUserIdAndProductId(Long userId, Long productId);
    
    /* @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END " +
            "FROM Order o " +
            "JOIN o.products products " +
            "WHERE o.user.id = :userId AND products.id = :productId")
     Boolean existsByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);*/
}