package com.devfm.offcialwebapp.repository;

import com.devfm.offcialwebapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Method to retrieve a list of all products
    public List<Product> findAll();

    // Method to retrieve a product by its ID
    public Optional<Product> findById(Long id);

    // Method to retrieve a product by its name
    public Product findByProductName(String nameProduct);
}
