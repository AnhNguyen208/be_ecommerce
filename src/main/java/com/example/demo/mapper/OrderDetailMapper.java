package com.example.demo.mapper;

import com.example.demo.dto.CartBookDto;
import com.example.demo.dto.OrderDetailDto;
import com.example.demo.entity.CartBook;
import com.example.demo.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(source = "orderDetail.book.id", target = "book_id")
    @Mapping(source = "orderDetail.quantity", target = "quantity")
    @Mapping(source = "orderDetail.book.price", target = "price")
    @Mapping(source = "orderDetail.book.title", target = "title")
    OrderDetailDto toOrderDetailDto(OrderDetail orderDetail);

//    CartBook toCartBook(CartItemDto cartItemDto);
}
