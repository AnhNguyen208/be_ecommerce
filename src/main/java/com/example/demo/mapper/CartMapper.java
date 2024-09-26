package com.example.demo.mapper;

import com.example.demo.dto.CartDto;
import com.example.demo.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toCart(CartDto cartDto);

    @Mapping(source = "cart.user.id", target = "user_id")
    CartDto toCartDto(Cart cart);
}
