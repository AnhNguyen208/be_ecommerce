package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookController {
    BookService bookService;

    @GetMapping
    ApiResponse<List<BookDto>> getBooks() {
        return ApiResponse.<List<BookDto>>builder()
                .result(bookService.getBooks())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<BookDto> getBookById(@PathVariable Long id) {
        return ApiResponse.<BookDto>builder()
                .result(bookService.getBookById(id))
                .build();
    }

    @GetMapping("/category/{id}")
    ApiResponse<List<BookDto>> getBookByCategoryId(@PathVariable Long id) {
        return ApiResponse.<List<BookDto>>builder()
                .result(bookService.getBookByCategoryId(id))
                .build();
    }

    @PostMapping
    ApiResponse<BookDto> createBook(@RequestBody @Valid BookDto bookDto) {
        return ApiResponse.<BookDto>builder()
                .result(bookService.createBook(bookDto))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return ApiResponse.<BookDto>builder()
                .result(bookService.updateBook(id, bookDto))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ApiResponse.<String>builder()
                .result("Book has been deleted")
                .build();
    }

    @GetMapping("/test")
    List<Book> test() {
        return bookService.test();
    }
}
