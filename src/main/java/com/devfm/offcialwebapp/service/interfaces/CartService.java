package com.devfm.offcialwebapp.service.interfaces;

import com.devfm.offcialwebapp.entity.Cart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CartService {

    // Retrieve a list of carts
    public List<Cart> getCarts();

    // Create a new cart
    public Cart createCart();

    // Retrieve a cart by its ID
    public Optional<Cart> getCart(Long id);

    // Update the flag status of a cart
    public Cart updateFlag(Long id) throws Exception;

    // Add a product to a cart
    Cart addProductToCart(Long cartId, String name, Integer quantity) throws Exception;
}
