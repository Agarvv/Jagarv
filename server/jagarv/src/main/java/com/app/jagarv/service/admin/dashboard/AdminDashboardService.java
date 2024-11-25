/* package com.app.jagarv.service.admin.dashboard;

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

@Service
public class AdminDashboardService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AdminDashboardMapper adminDashboardMapper;

    public AdminDashboardService(OrderRepository orderRepository, 
                                 UserRepository userRepository, 
                                 AdminDashboardMapper adminDashboardMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.adminDashboardMapper = adminDashboardMapper;
    }

    public AdminDashboardDTO getAdminDashboard() {
        String today = LocalDate.now().toString();

        Long ordersToday = orderRepository.getOrdersCountForToday(today);
        Long amountToday = orderRepository.getTotalAmountForToday(today);
        Long usersToday = userRepository.getUserRegistrationsToday(today);

        List<Object[]> mostOrderedProducts = orderRepository.getMostOrderedProducts();
        List<Object[]> ordersFromJulyToDecember =
         orderRepository.getOrdersBetweenDates("2024-07-01", "2024-12-31");

        List<MostOrderedProductDTO> productsDTO = mostOrderedProducts.stream()
                .map(adminMapper::toMostOrderedProductDTO)
                .collect(Collectors.toList());

        List<MonthlySalesDTO> monthlySales = ordersFromJulyToDecember.stream()
                .map(adminMapper::toMonthlySalesDTO)
                .collect(Collectors.toList());

        return new AdminDashboardDTO(ordersToday, amountToday, usersToday, monthlySales, productsDTO);
    }
} */