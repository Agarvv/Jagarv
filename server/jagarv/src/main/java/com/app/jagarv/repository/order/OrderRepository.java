package com.app.jagarv.repository.order;

import com.app.jagarv.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT COUNT(o) FROM Order o WHERE o.date = :today")
    Long getOrdersCountForToday(@Param("today") String today);

    @Query("SELECT SUM(o.amount) FROM Order o WHERE o.date = :today")
    Long getTotalAmountForToday(@Param("today") String today);

    @Query("SELECT o FROM Order o WHERE o.date BETWEEN :start AND :end")
    List<Order> getOrdersBetweenDates(@Param("start") String start, @Param("end") String end);

   // @Query("SELECT p.title, SUM(oi.quantity), p.price, p.stock " +
  //        "FROM Order o JOIN o.orderItems oi JOIN oi.product p " +
 //         "GROUP BY p.title, p.price, p.stock")
 //   List<Object[]> getMostOrderedProducts(); 

    List<Order> findAllByUser_Id(Long userId);

    Boolean existsByUserIdAndProducts_Id(Long userId, Long productId);
}