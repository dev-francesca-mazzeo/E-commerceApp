package com.devfm.offcialwebapp.repository;

import com.devfm.offcialwebapp.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Method to retrieve a list of all users
    public List<User> findAll();

    // Method to retrieve a user by its ID
    public Optional<User> findById(Long id);

    // Method to retrieve a list of users by a flag (e.g., deleted flag)
    public List<User> findAllByFlagDeleted(int flag);

    // Method to find a user by their name
    public User findUserByName(String name);
}
