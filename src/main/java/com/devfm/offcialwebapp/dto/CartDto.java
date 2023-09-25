package com.devfm.offcialwebapp.dto;

import com.devfm.offcialwebapp.entity.Cart;
import com.devfm.offcialwebapp.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CartDto {
    private Long id_cart;

    private Set<Product> addedProducts = new HashSet<>();

    // Method to convert Cart entity data into CartDto
    public CartDto convertDataIntoDTO(Cart cart) {
        CartDto cartDto = new CartDto();

        cartDto.setId_cart(cart.getId());
        cartDto.setAddedProducts(cart.getAddedProducts());

        return cartDto;
    }
}

