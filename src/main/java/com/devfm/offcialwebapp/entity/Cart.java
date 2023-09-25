package com.devfm.offcialwebapp.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "CART")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private Long id;

    @Column(name = "date")
    private Date date;

    @NotNull
    @Column(name = "flag_status")
    private String flag_Status;

    @ManyToMany
    @JoinTable(
            name = "added_products",
            joinColumns = @JoinColumn(name = "id_cart"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private Set<Product> addedProducts = new HashSet<>();

    // Method to add a product to the cart
    public void addProduct(Product product) {
        addedProducts.add(product);
    }
}
