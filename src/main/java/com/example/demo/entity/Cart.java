package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    Long id;

    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    User user;

    @OneToMany(mappedBy = "cart")
    @JsonIgnore
    Set<CartBook> cartBooks;

    int total_item;
    double total_amount;
}
