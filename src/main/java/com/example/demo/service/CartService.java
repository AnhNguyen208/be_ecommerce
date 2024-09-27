package com.example.demo.service;

import com.example.demo.dto.CartBookDto;
import com.example.demo.dto.CartDto;
import com.example.demo.entity.*;
import com.example.demo.mapper.CartBookMapper;
import com.example.demo.mapper.CartMapper;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CartBookRepository;
import com.example.demo.repository.CartRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartService {
    CartRepository cartRepository;
    BookRepository bookRepository;
    CartBookRepository cartBookRepository;
    CartBookMapper cartBookMapper;
    CartMapper cartMapper;


    public List<CartDto> getCarts() {
        return  cartRepository.findAll().stream()
                .map(cartMapper::toCartDto).toList();
    }

    public CartDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        CartDto cartDto = cartMapper.toCartDto(cart);

        if(cart.getCartBooks() != null) {
            Set<CartBookDto> cartBookDtos = new HashSet<>();
            for(CartBook cartBook: cart.getCartBooks()) {
                cartBookDtos.add(cartBookMapper.toCartBookDto(cartBook));
            }
            cartDto.setBooks(cartBookDtos);
        }

        return cartDto;
    }

    public CartDto getCartByUserId(Long id) {
        Cart cart = cartRepository.findByUserId(id);
        CartDto cartDto = cartMapper.toCartDto(cart);
        
        if(cart.getCartBooks() != null) {
            Set<CartBookDto> cartBookDtos = new HashSet<>();
            for(CartBook cartBook: cart.getCartBooks()) {
                cartBookDtos.add(cartBookMapper.toCartBookDto(cartBook));
            }
            cartDto.setBooks(cartBookDtos);
        }

        return cartDto;
    }

    public CartDto createCart(CartDto cartDto) {
        Cart cart = cartMapper.toCart(cartDto);
        cart = cartRepository.save(cart);
        return cartMapper.toCartDto(cart);
    }

    public CartDto addBookToCart(Long id, CartBookDto cartBookDto) {
        CartBookKey cartBookKey = new CartBookKey(id, cartBookDto.getBook_id());
        boolean isExists = cartBookRepository.existsById(cartBookKey);
        CartBook cartBook = new CartBook();

        if(isExists) {
            cartBook = cartBookRepository.findById(cartBookKey).orElseThrow();
            int quantity = cartBook.getQuantity();
            cartBook.setQuantity(quantity + 1);
        } else {
            cartBook.setId(cartBookKey);
            cartBook.setCart(cartRepository.findById(id).orElseThrow(RuntimeException::new));
            cartBook.setBook(bookRepository.findById(cartBookDto.getBook_id()).orElseThrow(RuntimeException::new));
            cartBook.setQuantity(1);
        }
        cartBookRepository.save(cartBook);

        updateCart(id);
        return getCartById(id);
    }

    public CartDto updateBookInCart(Long id, CartBookDto cartBookDto){
        CartBookKey cartBookKey = new CartBookKey(id, cartBookDto.getBook_id());
        CartBook cartBook = cartBookRepository.findById(cartBookKey).orElseThrow(RuntimeException::new);

        cartBook.setBook(bookRepository.findById(cartBookDto.getBook_id()).orElseThrow(RuntimeException::new));
        cartBook.setQuantity(cartBookDto.getQuantity());
        cartBookRepository.save(cartBook);
        updateCart(id);

        return getCartById(id);
    }

    public void removeBookFromCart(Long id, CartBookDto cartBookDto) {
        CartBookKey cartBookKey = new CartBookKey(id, cartBookDto.getBook_id());
        CartBook cartBook = cartBookRepository.findById(cartBookKey)
                .orElseThrow();
        cartBookRepository.delete(cartBook);
        updateCart(id);
    }

    private void updateCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.setTotal_item(cart.getCartBooks().toArray().length);
        cart.setTotal_amount(cart.getCartBooks().stream()
                .mapToDouble((item) -> item.getBook().getPrice() * item.getQuantity())
                .sum());

        cartRepository.save(cart);
    }

    public List<Cart> test() {
        return  cartRepository.findAll();
    }
}
