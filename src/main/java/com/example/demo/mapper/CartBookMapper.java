package com.example.demo.mapper;

import com.example.demo.dto.CartBookDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.CartBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CartBookMapper {

    @Mapping(source = "cartBook.book.id", target = "book_id")
    @Mapping(source = "cartBook.quantity", target = "quantity")
    @Mapping(source = "cartBook.book.price", target = "price")
    CartBookDto toCartBookDto(CartBook cartBook);

//    CartBook toCartBook(CartItemDto cartItemDto);
}
