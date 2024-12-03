package com.app.jagarv.service.admin.dashboard;

import com.app.jagarv.dto.admin.dashboard.AdminDashboardDTO;
import com.app.jagarv.dto.product.read.MostOrderedProductDTO;
import com.app.jagarv.dto.sales.read.MonthlySalesDTO;
import com.app.jagarv.repository.order.OrderRepository;
import com.app.jagarv.repository.user.UserRepository;
import com.app.jagarv.mapper.admin.AdminDashboardMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        Map<String, Object> dashboardData = new HashMap<>(); // just for now 
        dashboardData.put("ordersToday", orderRepository.findOrdersToday());
        dashboardData.put("totalEarningsToday", orderRepository.getTotalEarningsToday());
        dashboardData.put("mostOrderedProducts", orderRepository.findMostOrderedProducts());
        dashboardData.put("orderCountByMonth", orderRepository.getOrderCountByMonth());
        dashboardData.put("usersToday", userRepository.findUsersToday());
        return dashboardData;
    }
}