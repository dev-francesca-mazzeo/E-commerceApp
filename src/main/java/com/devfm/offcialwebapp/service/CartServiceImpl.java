package com.devfm.offcialwebapp.service;

import com.devfm.offcialwebapp.costants.Utility;
import com.devfm.offcialwebapp.dto.CartDto;
import com.devfm.offcialwebapp.entity.*;
import com.devfm.offcialwebapp.repository.*;
import com.devfm.offcialwebapp.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    Utility utils = new Utility();

    CartDto cartDto = new CartDto();

    @Override
    public List<Cart> getCarts() {
        // Retrieve a list of all carts
        return cartRepository.findAll();
    }

    @Override
    public Cart createCart() {
        // Create a new cart and set its date and status
        Cart cartToAdd = new Cart();
        cartToAdd.setDate(utils.generateCurrentDate());
        cartToAdd.setFlag_Status("C");

        // Save the new cart to the repository
        return cartRepository.save(cartToAdd);
    }

    @Override
    public Optional<Cart> getCart(Long id) {
        // Retrieve a cart by its ID
        return cartRepository.findById(id);
    }

    @Override
    public Cart updateFlag(@PathVariable Long id) throws Exception {
        // Find a cart by ID and update its status to "D" (deleted)
        Cart updateFlag = cartRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Not Found Id"));
        updateFlag.setFlag_Status("D");
        return cartRepository.save(updateFlag);
    }

    @Override
    public Cart addProductToCart(Long cartId, String name, Integer quantity) throws Exception {
        // Find the cart by its ID
        Cart cart = cartRepository
                .findById(cartId)
                .orElseThrow(() -> new Exception("Not Found Cart"));

        // Check if the cart has been deleted
        if (cart.getFlag_Status().equals("D")) {
            throw new Exception("Cart deleted");
        } else {
            // Find the product by name and update its quantity
            Product product = productRepository.findByProductName(name);
            product.setQuantity(quantity);

            // Add the product to the cart and save it
            cart.addProduct(product);
            return cartRepository.save(cart);
        }
    }

    public List<CartDto> getAllCartProduct() {
        // Convert a list of carts into a list of DTOs
        return ((List<Cart>) cartRepository
                .findAll())
                .stream()
                .map(cart -> cartDto.convertDataIntoDTO(cart))
                .collect(Collectors.toList());
    }
}
