package com.app.jagarv.service.admin.dashboard;

import com.app.jagarv.dto.product.read.BestSellerDTO;
import com.app.jagarv.repository.order.OrderRepository;
import com.app.jagarv.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        List<BestSellerDTO> bestSellersDTO = new ArrayList<>();
        List<Object[]> mostOrderedProducts = orderRepository.findMostOrderedProducts(); 
        for(Object[] mostOrdered: mostOrderedProducts) {
            Long productId = (Long) mostOrdered[0];
            String title = (String) mostOrdered[1];
            String pictures = (String) mostOrdered[2];
            Long stock = (Long) mostOrdered[3];
            BigDecimal price = (BigDecimal) mostOrdered[4];
            Long selled = (Long) mostOrdered[5];
            String[] picturesArray = pictures.split(",");  
            BestSellerDTO dto = new BestSellerDTO(productId, title, picturesArray, stock, price, selled);
            bestSellersDTO.add(dto);
        }
        
        dashboardData.put("mostOrderedProducts", bestSellersDTO); 
        
        dashboardData.put("orderCountByMonth", orderRepository.getOrderCountByMonth());
        
        dashboardData.put("usersToday", userRepository.countUsersToday());
        
        dashboardData.put("totalIncome", orderRepository.getTotalIncome()); 
        
        return dashboardData;
    }
}