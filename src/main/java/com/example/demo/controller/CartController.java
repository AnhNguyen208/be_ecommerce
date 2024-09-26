package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.CartBookDto;
import com.example.demo.dto.CartDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartBook;
import com.example.demo.service.CartService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartController {
    CartService cartService;

    @GetMapping
    ApiResponse<List<CartDto>> getCarts() {
        return ApiResponse.<List<CartDto>>builder()
                .result(cartService.getCarts())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<CartDto> getCartById(@PathVariable Long id) {
        return ApiResponse.<CartDto>builder()
                .result(cartService.getCartById(id))
                .build();
    }

    @GetMapping("user/{id}")
    ApiResponse<CartDto> getCartByUserId(@PathVariable Long id) {
        return ApiResponse.<CartDto>builder()
                .result(cartService.getCartByUserId(id))
                .build();
    }

    @PostMapping
    ApiResponse<CartDto> createCart(@RequestBody @Valid CartDto cartDto) {
        return ApiResponse.<CartDto>builder()
                .result(cartService.createCart(cartDto))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ApiResponse.<String>builder()
                .result("Book has been deleted")
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<CartDto> updateCart(@PathVariable Long id, @RequestBody CartBookDto cartBookDto) {
        return ApiResponse.<CartDto>builder()
                .result(cartService.updateCart(id, cartBookDto))
                .build();
    }

    @PostMapping("/{id}")
    ApiResponse<CartDto> addBook(@PathVariable Long id, @RequestBody CartBookDto cartBookDto) {
        return ApiResponse.<CartDto>builder()
                .result(cartService.addBook(id, cartBookDto))
                .build();
    }

    @GetMapping("/test")
    List<Cart> test() {
        return cartService.test();
    }
}
