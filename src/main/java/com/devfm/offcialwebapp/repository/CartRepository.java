package com.devfm.offcialwebapp.repository;

import com.devfm.offcialwebapp.dto.CartDto;
import com.devfm.offcialwebapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Method to retrieve a list of all carts
    public List<Cart> findAll();

    // Method to retrieve a cart by its ID
    public Optional<Cart> findById(Long id);
}
