package com.devfm.offcialwebapp.dto;

import com.devfm.offcialwebapp.entity.Cart;
import com.devfm.offcialwebapp.security.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String name;

    private Long id_cart;

    // Method to convert User entity data into UserDto
    public UserDto convertDataIntoDTO(User user) {
        UserDto dto = new UserDto();

        dto.setName(user.getName());

        // Create a Cart object (This may need modification based on your logic)
        Cart cart = new Cart();
        dto.setId_cart(cart.getId());

        return dto;
    }
}

