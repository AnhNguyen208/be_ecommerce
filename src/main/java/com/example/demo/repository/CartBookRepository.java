package com.example.demo.repository;

import com.example.demo.entity.CartBook;
import com.example.demo.entity.CartBookKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartBookRepository extends JpaRepository<CartBook, CartBookKey> {
}
