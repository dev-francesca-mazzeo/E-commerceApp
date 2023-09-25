package com.devfm.offcialwebapp.controller.interfaces;

import com.devfm.offcialwebapp.entity.Product;
import static com.devfm.offcialwebapp.costants.Endpoint.*;
import static com.devfm.offcialwebapp.costants.Endpoint.PRODUCT;

import com.devfm.offcialwebapp.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PRODUCT)
public interface ProductController {

    // Endpoint to retrieve a list of products
    @GetMapping(value = "/get-product")
    public List<Product> getproducts();

    // Endpoint to add a new product
    @PostMapping(value = "/add-product")
    public Product addProduct(@RequestBody Product addProduct);

    // Endpoint to delete a product by its ID
    @DeleteMapping(value = "/delete-product/{id}")
    public String deleteProduct(Long id);

    // Endpoint to retrieve a product by its ID
    @GetMapping(value = "/get-product/{id}")
    public Optional<Product> getProduct(Long id);

    // Endpoint to update a product's name by its ID
    @PutMapping(value = "/update/{id}")
    public Optional<Product> updateProduct(Long id, @RequestBody String name) throws Exception;
}
