package com.example.demo.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDto {
    Long id;
    Long user_id;
    Set<CartBookDto> books;
    int total_item;
    double total_amount;
}
