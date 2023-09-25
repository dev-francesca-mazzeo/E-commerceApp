package com.devfm.offcialwebapp.controller;

import com.devfm.offcialwebapp.controller.interfaces.CartController;
import com.devfm.offcialwebapp.dto.CartDto;
import com.devfm.offcialwebapp.entity.Cart;
import com.devfm.offcialwebapp.repository.CartRepository;
import com.devfm.offcialwebapp.repository.ProductRepository;
import com.devfm.offcialwebapp.repository.UserRepository;
import com.devfm.offcialwebapp.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CartControllerImpl implements CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // Endpoint to retrieve a list of carts
    @Override
    public List<Cart> getCarts() {
        return cartServiceImpl.getCarts();
    }

    // Endpoint to create a new cart
    @Override
    public Cart createCart() {
        return cartServiceImpl.createCart();
    }

    // Endpoint to retrieve a cart by its ID
    @Override
    public Optional<Cart> getcart(Long id) {
        return cartServiceImpl.getCart(id);
    }

    // Endpoint to update a flag for a cart
    @Override
    public Cart updateFlag(Long id) throws Exception {
        return cartServiceImpl.updateFlag(id);
    }

    // Endpoint to add a product to a cart
    @Override
    public Cart addProductToCart(Long cartId, String name, Integer quantity) throws Exception {
        return cartServiceImpl.addProductToCart(cartId, name, quantity);
    }

    // Endpoint to retrieve a list of CartDto objects
    @Override
    public List<CartDto> getAllCartproductgetAllCartProduct() {
        List<CartDto> cartproducts = cartServiceImpl.getAllCartProduct();
        return cartproducts;
    }
}
