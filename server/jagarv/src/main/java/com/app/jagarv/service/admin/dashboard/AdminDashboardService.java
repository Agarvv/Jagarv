package com.app.jagarv.service.admin.dashboard;

import com.app.jagarv.repository.order.OrderRepository;
import com.app.jagarv.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminDashboardService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public AdminDashboardService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Map<String, Object> getAdminDashboard() {
        Map<String, Object> dashboardData = new HashMap<>();
        
        dashboardData.put("ordersToday", orderRepository.countOrdersToday()); 
        
        dashboardData.put("totalEarningsToday", orderRepository.getTotalEarningsToday()); 
        
        dashboardData.put("mostOrderedProducts", orderRepository.findMostOrderedProducts()); 
        
        dashboardData.put("orderCountByMonth", orderRepository.getOrderCountByMonth());
        
        dashboardData.put("usersToday", userRepository.countUsersToday()); // Solo el número de usuarios registrados hoy
        
        return dashboardData;
    }
}