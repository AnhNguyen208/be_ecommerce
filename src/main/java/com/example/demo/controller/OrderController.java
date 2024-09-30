package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@CrossOrigin
public class OrderController {
    OrderService orderService;

    @GetMapping
    ApiResponse<List<OrderDto>> getOrders() {
        return ApiResponse.<List<OrderDto>>builder()
                .result(orderService.getOrders())
                .build();
    }

    @PostMapping("/{id}")
    ApiResponse<OrderDto> createOrder(@PathVariable Long id) {
        return ApiResponse.<OrderDto>builder()
                .result(orderService.createOrder(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<String> checkout(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .result("Thanh toán thành công")
                .build();
    }
}
