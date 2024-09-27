package com.example.demo.mapper;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderDto orderDto);

    @Mapping(source = "order.user.id", target = "user_id")
    OrderDto toOrderDto(Order order);
}
