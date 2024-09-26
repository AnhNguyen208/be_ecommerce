package com.example.demo.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {
    Long id;
    String title;
    String author;
    String publisher;
    int year;
    double price;
    int stock;
    String description;
    String imageUrl;
    Long category_id;
}
