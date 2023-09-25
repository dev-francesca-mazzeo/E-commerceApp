package com.devfm.offcialwebapp.controller;

import com.devfm.offcialwebapp.entity.Product;
import com.devfm.offcialwebapp.service.ProductServiceImpl;
import com.devfm.offcialwebapp.controller.interfaces.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Optional;

import static com.devfm.offcialwebapp.costants.Endpoint.PRODUCT;

@RestController
@RequestMapping(PRODUCT)
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductServiceImpl productService;

    // Endpoint to retrieve a list of products
    @Override
    public List<Product> getproducts() {
        return productService.getproducts();
    }

    // Endpoint to add a new product
    @Override
    public Product addProduct(@RequestBody Product addProduct) {
        return productService.addProduct(addProduct);
    }

    // Endpoint to delete a product by its ID
    @Override
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Deleted Product!";
    }

    // Endpoint to retrieve a product by its ID
    @Override
    public Optional<Product> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    // Endpoint to update a product's name by its ID
    @Override
    public Optional<Product> updateProduct(@PathVariable Long id, @RequestBody String name) throws Exception {
        productService.updateProduct(id, name);
        return productService.getProduct(id);
    }
}
