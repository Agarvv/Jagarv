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

    @Query(value = "SELECT p.id, p.title, COUNT(o.id) AS order_count " +
                   "FROM products p " +
                   "JOIN order_product op ON op.product_id = p.id " +
                   "JOIN orders o ON o.id = op.order_id " +
                   "GROUP BY p.id " +
                   "ORDER BY order_count DESC", nativeQuery = true)
    List<Object[]> findMostOrderedProducts();

    @Query(value = "SELECT EXTRACT(MONTH FROM date::DATE) AS month, COUNT(*) AS order_count " +
                   "FROM orders " +
                   "WHERE EXTRACT(MONTH FROM date::DATE) BETWEEN 7 AND 12 " +
                   "GROUP BY month " +
                   "ORDER BY month", nativeQuery = true)
    List<Object[]> getOrderCountByMonth();

    List<Order> findAllByUser_Id(Long userId);

    Boolean existsByUserIdAndProducts_Id(Long userId, Long productId);
}