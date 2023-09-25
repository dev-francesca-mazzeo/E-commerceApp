package com.devfm.offcialwebapp.repository;

import com.devfm.offcialwebapp.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Method to find a role by its name
    public Role findRoleByName(String name);
}
