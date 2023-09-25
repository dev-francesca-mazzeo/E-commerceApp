package com.devfm.offcialwebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_cost")
    private Float productCost;

    @Column(name = "quantity")
    private Integer quantity;

    @JsonIgnore
    @ManyToMany(mappedBy = "addedProducts")
    private Set<Cart> cart = new HashSet<>();
}
