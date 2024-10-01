package com.example.demo.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBook(BookDto bookDto);

    @Mapping(source = "book.category.id", target = "category_id")
    @Mapping(source = "book.category.name", target = "category_name")
    BookDto toBookDto(Book book);
}
