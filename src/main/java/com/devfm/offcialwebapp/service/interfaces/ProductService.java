package com.devfm.offcialwebapp.service.interfaces;

import com.devfm.offcialwebapp.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    // Retrieve a list of products
    public List<Product> getproducts();

    // Add a new product
    public Product addProduct(Product addProduct);

    // Delete a product by its ID
    public void deleteProduct(Long id);

    // Retrieve a product by its ID
    public Optional<Product> getProduct(Long id);

    // Update a product's name by its ID
    public void updateProduct(Long id, String name) throws Exception;

    // Retrieve a product by its name
    public Product getProductByName(String nameProduct);
}
