package com.devfm.offcialwebapp.controller.interfaces;

import com.devfm.offcialwebapp.dto.CartDto;
import com.devfm.offcialwebapp.entity.Cart;
import static com.devfm.offcialwebapp.costants.Endpoint.*;
import static com.devfm.offcialwebapp.costants.Endpoint.CART;

import com.devfm.offcialwebapp.entity.Cart;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CART)
public interface CartController {

    // Endpoint to retrieve a list of carts
    @GetMapping(value = "/get-carts")
    public List<Cart> getCarts();

    // Endpoint to create a new cart
    @PostMapping(value= "/create-new-cart")
    public Cart createCart();

    // Endpoint to retrieve a cart by its ID
    @GetMapping(value = "/get-cart/{id}")
    public Optional<Cart> getcart(@PathVariable Long id);

    // Endpoint to update a flag for a cart
    @PutMapping(value = "/update-flag/{id}")
    public Cart updateFlag(@PathVariable Long id) throws Exception;

    // Endpoint to add a product to a cart
    @PutMapping("/add-product-to-cart/{cartId}/product")
    Cart addProductToCart(@PathVariable Long cartId, @RequestParam String name, @RequestBody Integer quantity) throws Exception;

    // Endpoint to retrieve a list of CartDto objects
    @GetMapping(value = "/map")
    public List<CartDto> getAllCartproductgetAllCartProduct();

}
