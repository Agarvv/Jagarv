package com.app.jagarv.service.admin.order;

import java.util.List;
import java.util.ArrayList;

import com.app.jagarv.dto.order.edit.SetOrderDTO;
import com.app.jagarv.dto.order.read.OrdersDTO;
import com.app.jagarv.entity.cart.Cart;
import com.app.jagarv.entity.cart.CartItem;
import com.app.jagarv.entity.order.Order;
import com.app.jagarv.entity.product.Product;
import com.app.jagarv.entity.user.User;
import com.app.jagarv.exception.exceptions.cart.CartNotFoundException;
import com.app.jagarv.exception.exceptions.order.OrderNotFoundException;
import com.app.jagarv.exception.exceptions.users.UserNotFoundException;
import com.app.jagarv.mapper.orders.OrdersMapper;
import com.app.jagarv.outil.SecurityOutil;
import com.app.jagarv.repository.cart.CartRepository;
import com.app.jagarv.repository.order.OrderRepository;
import com.app.jagarv.repository.user.UserRepository;
import com.app.jagarv.dto.order.read.AdminOrderDTO;

import org.springframework.stereotype.Service;

import com.app.jagarv.dto.payments.ProductPaymentDTO; 

import com.app.jagarv.service.cart.CartService;

import com.app.jagarv.service.user.UserService; 

import java.util.stream.Collectors;

import java.time.LocalDate;

// Our App's Orders Service.
@Service
public class AdminOrdersService {
    // Injections
    private final OrderRepository orderRepository; 
    private final OrdersMapper ordersMapper; 
    private final SecurityOutil securityOutil; 
    private final CartRepository cartRepository; 
    private final UserRepository userRepository;
    private final CartService cartService;
    private final UserService userService; 
    
    public AdminOrdersService
    (OrderRepository orderRepository,
     OrdersMapper ordersMapper,
     CartRepository cartRepository,
     SecurityOutil securityOutil,
     UserRepository userRepository,
     CartService cartService,
     UserService userService 
    ) {
      this.orderRepository = orderRepository;
      this.ordersMapper = ordersMapper;
      this.cartRepository = cartRepository;
      this.securityOutil = securityOutil;  
      this.cartService = cartService;
      this.userRepository = userRepository;
      this.userService = userService; 
    }
    
    // Returns all the App's orders to the controller
    public List<AdminOrderDTO> getOrders() {
        return orderRepository.findAll()           .stream()
            .map(ordersMapper::orderToAdminOrder)
            .collect(Collectors.toList());
    }

    // places a order, will be used on the payments services when the payment is succeded

    public void placeOrder(Long amount, String paymentIntentId, String method) {
      
      User user = userService.findAuthenticatedUser(); 
      
      if(user == null) {
          throw new NullPointerException("User is null in order"); 
      }
      
      Cart cart = cartService.getUserRawCart();
      
      if(cart == null) {
          throw new NullPointerException("User cart is null..");
      }

      List<Product> products = new ArrayList<>();
      for(CartItem item: cart.getCartItems()) {
         products.add(item.getProduct());
         item.getProduct().setStock(item.getProduct().getStock() -1);
      }
      
      Order order = new Order();
      order.setUser(user);
      order.setProducts(products);
      order.setStatus("PREPARING");
      order.setAmount(amount);
      order.setPaymentId(paymentIntentId);
      order.setAdress(user.getAdress()); 
      order.setDate(LocalDate.now().toString()); 
      order.setMethod(method); 
      
      orderRepository.save(order);

      cart.clearCart();
    }
    
    // sets a order status as in proccess
    public void setOrderInProcces(SetOrderDTO orderDTO) {
      Order order = findOrderById(orderDTO.getOrderId());
      order.setStatus("PROCESSING");
      orderRepository.save(order);
    }
    
    // sets a order status as arrived
    public void setOrderArrived(SetOrderDTO orderDTO) {
      Order order = findOrderById(orderDTO.getOrderId());
      order.setStatus("ARRIVED");
      orderRepository.save(order);
    }

    private Order findOrderById(Long id) {
      Order order = orderRepository.findById(id).orElseThrow(()
       -> new OrderNotFoundException("Could not find order")
      ); 

      return order;
    }
}
