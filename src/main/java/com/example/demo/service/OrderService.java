package com.example.demo.service;

import com.example.demo.dto.OrderDetailDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.entity.*;
import com.example.demo.mapper.BookMapper;
import com.example.demo.mapper.OrderDetailMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService {
    UserRepository userRepository;
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    OrderDetailMapper orderDetailMapper;
    CartBookRepository cartBookRepository;

    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order order : orders) {
            orderDtos.add(mapping(order));
        }
        return orderDtos;
    }

    public OrderDto createOrder(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        Set<CartBook> cartBooks = user.getCart().getCartBooks();

        clearCart(cartBooks);

        Cart cart = user.getCart();
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setStatus(0);
        order.setTotalAmount(cart.getTotal_amount());
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartBook cartBook : cartBooks) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBook(cartBook.getBook());
            orderDetail.setQuantity(cartBook.getQuantity());
            orderDetail.setPrice(cartBook.getBook().getPrice());
            orderDetail.setOrder(order);

            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);

        order = orderRepository.save(order);

        return mapping(order);
    }


    private void clearCart(Set<CartBook> cartBooks) {
        cartBookRepository.deleteAll(cartBooks);
    }
    private OrderDto mapping(Order order) {
        OrderDto orderDto = orderMapper.toOrderDto(order);
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        for(OrderDetail orderDetail: order.getOrderDetails()) {
            orderDetailDtos.add(orderDetailMapper.toOrderDetailDto(orderDetail));
        }
        orderDto.setOrderDetailDtos(orderDetailDtos);

        return orderDto;
    }

    public void checkout(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(1);

        orderRepository.save(order);
    }

}
