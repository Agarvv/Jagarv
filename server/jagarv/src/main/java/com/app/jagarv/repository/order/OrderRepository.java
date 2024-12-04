package com.app.jagarv.repository.order;

import com.app.jagarv.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.jagarv.dto.product.read.BestSellerDTO;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT COUNT(o) FROM Order o WHERE CAST(o.date AS java.sql.Date) = CURRENT_DATE")
    Long countOrdersToday();

    @Query(value = "SELECT SUM(amount) FROM orders WHERE date::DATE = CURRENT_DATE", nativeQuery = true)
    Long getTotalEarningsToday();

    @Query(value = "SELECT new com.tu.paquete.BestSellerDTO(" +
               "p.id, p.title, GROUP_CONCAT(pp.pictures), p.stock, p.price, COUNT(opci.cart_item_id)) " +
               "FROM Product p " +
               "JOIN p.cartItems ci " +
               "JOIN ci.orderProductCartItems opci " +
               "JOIN opci.order o " +
               "JOIN p.pictures pp " +
               "GROUP BY p.id, p.title, p.stock, p.price " +
               "ORDER BY COUNT(opci.cart_item_id) DESC")
    List<BestSellerDTO> findMostOrderedProducts();

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