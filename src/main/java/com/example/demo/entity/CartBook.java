package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cart_book")
public class CartBook {
    @EmbeddedId
    CartBookKey id;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    @JsonIgnore
    Cart cart;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    @JsonBackReference
    @JsonIgnore
    Book book;

    int quantity;
}
