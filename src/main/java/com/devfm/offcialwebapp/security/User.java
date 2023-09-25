package com.devfm.offcialwebapp.security;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "USERS_SISP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name_user")
    private String name;

    @Column(name = "psw", nullable = false)
    private String psw;

    @Column(name="enable_request")
    private int enableRequest;

    // Value 0 for active users
    // Value 1 for inactive users
    @Column(name = "flag_deleted")
    private int flagDeleted;

    @ManyToMany
    @JoinTable(
            name="added_roles",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> addedRoles = new HashSet<>();

    // Method to add roles to the user
    public void addRoles(Role role) {
        addedRoles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name_user='" + name + '\'' +
                ", psw='" + psw + '\'' +
                ", enable_request=" + enableRequest +
                '}';
    }
}
