package com.example.demo.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    Long id;
    Long user_id;
    List<OrderDetailDto> orderDetailDtos;
    LocalDateTime orderDate;
    double totalAmount;
    int status;
}
