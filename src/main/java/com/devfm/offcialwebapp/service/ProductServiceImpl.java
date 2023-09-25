package com.devfm.offcialwebapp.service;

import com.devfm.offcialwebapp.entity.Product;
import com.devfm.offcialwebapp.repository.ProductRepository;
import com.devfm.offcialwebapp.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getproducts() {
        // Retrieve a list of all products
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product addProduct) {
        // Save a new product to the repository
        return productRepository.save(addProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        // Delete a product by its ID
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        // Retrieve a product by its ID
        return productRepository.findById(id);
    }

    @Override
    public void updateProduct(Long id, String name) throws Exception {
        // Find a product by ID and update its name
        Product updateProduct = productRepository.findById(id).orElseThrow(() -> new Exception("Not Found Product!"));
        updateProduct.setProductName(name);
        productRepository.save(updateProduct);
    }

    @Override
    public Product getProductByName(String nameProduct) {
        // Retrieve a product by its name
        return productRepository.findByProductName(nameProduct);
    }
}
