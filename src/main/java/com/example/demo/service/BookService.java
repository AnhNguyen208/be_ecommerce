package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookService {
    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    BookMapper bookMapper;

    public List<BookDto> getBooks() {
        return  bookRepository.findAll().stream()
                .map(bookMapper::toBookDto).toList();
    }

    public BookDto getBookById(Long id) {
        return bookMapper.toBookDto(
                bookRepository.findById(id)
                        .orElseThrow(RuntimeException::new));
    }

    public List<BookDto> getBookByCategoryId(Long id) {
        return bookRepository.findByCategoryId(id).stream()
                .map(bookMapper::toBookDto).toList();
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.toBook(bookDto);
        book.setCategory(
                categoryRepository.findById(
                        bookDto.getCategory_id())
                        .orElseThrow(RuntimeException::new));
        book = bookRepository.save(book);
        return bookMapper.toBookDto(book);
    }

    public BookDto updateBook(Long id, BookDto bookDto){
        Book book = bookMapper.toBook(bookDto);
        book.setId(id);
        book.setCategory(
                categoryRepository.findById(
                                bookDto.getCategory_id())
                        .orElseThrow(RuntimeException::new));
        book = bookRepository.save(book);

        return bookMapper.toBookDto(book);
    }

    public List<BookDto> search(String keyword) {
        return bookRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword).stream()
                .map(bookMapper::toBookDto).toList();
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> test() {
        return  bookRepository.findAll();
    }
}
