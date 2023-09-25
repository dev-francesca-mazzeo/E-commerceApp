package com.devfm.offcialwebapp.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity(name= "ROLE")
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "addedRoles")
    private Set<User> user = new HashSet<>();
}
